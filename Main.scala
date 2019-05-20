package wmp.taxi_blog

import java.sql.{Connection, DriverManager}

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import wmp.taxi_blog.sql_queries._
import wmp.taxi_blog.sql_server_queries._
import anorm._
import java.time.Instant

import Array._
import com.typesafe.config.ConfigFactory

case class DBType(targetDataSourceClassName: String, connectionString: String )

  object Main extends App {

    //TODO: change these from config to args
    val jdbcDriverType = ConfigFactory.load().getString("jdbcDriverType")
    val serverSize = ConfigFactory.load().getString("serverSize")

    val targetDBType = jdbcDriverType.toLowerCase() match {
      case "sql_azuredw" =>
        DBType(ConfigFactory.load().getString("azureDriver"),ConfigFactory.load().getString("azureConnectionString"))

      case "sql_server" =>
        DBType(ConfigFactory.load().getString("sqlServerDriver"),ConfigFactory.load().getString("sqlServerConnectionString"))

      case "redshift" =>
        DBType(ConfigFactory.load().getString("redshiftDriver"),ConfigFactory.load().getString("redshiftConnectionString"))

      case "brytlyt" =>
        DBType(ConfigFactory.load().getString("brytlytDriver"),ConfigFactory.load().getString("brytlytConnectionString"))

      case "snowflake" =>
        DBType(ConfigFactory.load().getString("snowflakeDriver"),ConfigFactory.load().getString("snowflakeConnectionString"))
    }

    Class.forName(targetDBType.targetDataSourceClassName)
    val conn = DriverManager.getConnection(targetDBType.connectionString)

    Class.forName(ConfigFactory.load().getString("resultDriver"))
    val resConn = DriverManager.getConnection(ConfigFactory.load().getString("resultConnectionString"))

    //pick which queries to use (SQL Server is special)
    val testQuery:Array[String]  =
      if(jdbcDriverType != "sql_server" && jdbcDriverType != "sql_azuredw") Array(simpleSQL,normalSQL,complexSQL)
      else Array(simpleSQLServer,normalSQLServer,complexSQLServer)

    val tableList = List(("S","nyc_taxi_data_small_n"),("M","nyc_taxi_data_medium_n"),("L","nyc_taxi_data_large_n"))

    if (jdbcDriverType.toLowerCase() == "redshift") {
      SQL("SET enable_result_cache_for_session to off;").execute()(conn)
    }

    for (rep <- 0 to 2) { // 3 repetitions
      for (tbl <- tableList) { // loop over tables
        for (i <- 0 to 2) { // loop over queries
          val qStartTime = System.currentTimeMillis()
          val tQuery = testQuery(i).replaceAllLiterally("#$table", tbl._2)

          SQL(tQuery).execute()(conn)
          // save result
          /*insertStatement.on(
            'serverSize -> serverSize,
            'queryId -> (i + 1),
            'tableSize -> tbl._1,
            'duration -> (System.currentTimeMillis() - qStartTime),
            'jdbcDriverType -> jdbcDriverType
          ).execute()(resConn)*/
        }
      }
    }
  }