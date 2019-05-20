package wmp.taxi_blog

import anorm.{SQL, SqlQuery}

object sql_server_queries {

  val simpleSQLServer: String =
    """SELECT Sum([a0]) AS [a0]
      FROM   ( (SELECT [t0].[year]              AS [c28],
                     [t0].[month]             AS [c29],
                     Sum([t0].[total_amount]) AS [a0]
              FROM   (SELECT [t0].[total_amount]                 AS [total_amount],
                             Year([t0].[tpep_dropoff_datetime])  AS [Year],
                             Month([t0].[tpep_dropoff_datetime]) AS [Month]
                      FROM   ((SELECT [__table].[vendorid]              AS [vendorid],
                                      [__table].[tpep_pickup_datetime]  AS
                                      [tpep_pickup_datetime],
                                      [__table].[tpep_dropoff_datetime] AS
                                      [tpep_dropoff_datetime],
                                      [__table].[passenger_count]       AS
                                      [passenger_count],
                                      [__table].[trip_distance]         AS
                                      [trip_distance],
                                      [__table].[ratecodeid]            AS [ratecodeid]
                                      ,
      [__table].[store_and_fwd_flag]    AS
      [store_and_fwd_flag],
      [__table].[pulocationid]          AS [pulocationid],
      [__table].[dolocationid]          AS [dolocationid],
      [__table].[payment_type]          AS [payment_type],
      [__table].[fare_amount]           AS [fare_amount],
      [__table].[extra]                 AS [extra],
      [__table].[mta_tax]               AS [mta_tax],
      [__table].[tip_amount]            AS [tip_amount],
      [__table].[tolls_amount]          AS [tolls_amount],
      [__table].[improvement_surcharge] AS [improvement_surcharge],
      [__table].[total_amount]          AS [total_amount],
      [__table].[s_row_id]              AS [s_row_id],
      [__table].[s_timestamp]           AS [s_timestamp],
      [__table].[s_input_id]            AS [s_input_id]
      FROM   #$table AS [__table])) AS [t0]) AS
      [t0]
      GROUP  BY [t0].[year],
      [t0].[month]) AS [basetable0]
      INNER JOIN ((SELECT 2018 AS [c28],
      7    AS [c29])
      UNION ALL
      (SELECT 2018 AS [c28],
      1    AS [c29])
      UNION ALL
      (SELECT 2018 AS [c28],
      2    AS [c29])) AS [semijoin1]
      ON ( ( [semijoin1].[c28] = [basetable0].[c28] )
      AND ( [semijoin1].[c29] = [basetable0].[c29] ) ) )

      """


  val normalSQLServer: String =
    """
      SELECT TOP (1000001) [t0].[year],
                           [t0].[month],
                           Sum([t0].[total_amount]) AS [a0]
      FROM   (SELECT [t0].[total_amount]                 AS [total_amount],
                     Year([t0].[tpep_dropoff_datetime])  AS [Year],
                     Month([t0].[tpep_dropoff_datetime]) AS [Month]
              FROM   ((SELECT [__table].[vendorid]              AS [vendorid],
                              [__table].[tpep_pickup_datetime]  AS
                              [tpep_pickup_datetime],
                              [__table].[tpep_dropoff_datetime] AS
                              [tpep_dropoff_datetime],
                              [__table].[passenger_count]       AS [passenger_count],
                              [__table].[trip_distance]         AS [trip_distance],
                              [__table].[ratecodeid]            AS [ratecodeid],
                              [__table].[store_and_fwd_flag]    AS [store_and_fwd_flag]
                              ,
                              [__table].[pulocationid]
                              AS [pulocationid],
                              [__table].[dolocationid]          AS [dolocationid],
                              [__table].[payment_type]          AS [payment_type],
                              [__table].[fare_amount]           AS [fare_amount],
                              [__table].[extra]                 AS [extra],
                              [__table].[mta_tax]               AS [mta_tax],
                              [__table].[tip_amount]            AS [tip_amount],
                              [__table].[tolls_amount]          AS [tolls_amount],
                              [__table].[improvement_surcharge] AS
                              [improvement_surcharge],
                              [__table].[total_amount]          AS [total_amount],
                              [__table].[s_row_id]              AS [s_row_id],
                              [__table].[s_timestamp]           AS [s_timestamp],
                              [__table].[s_input_id]            AS [s_input_id]
                       FROM   #$table AS [__table])) AS [t0]) AS
             [t0]
      WHERE  ( ( ( ( ( ( [t0].[year] = 2008 )
                       AND ( [t0].[month] = 12 ) )
                      OR ( ( [t0].[year] = 2018 )
                           AND ( [t0].[month] = 7 ) ) )
                    OR ( ( ( [t0].[year] = 2018 )
                           AND ( [t0].[month] = 1 ) )
                          OR ( ( [t0].[year] = 2018 )
                               AND ( [t0].[month] = 2 ) ) ) )
                  OR ( ( ( [t0].[year] = 2009 )
                         AND ( [t0].[month] = 1 ) )
                        OR ( ( [t0].[year] = 2003 )
                             AND ( [t0].[month] = 1 ) ) ) )
                OR ( ( ( ( [t0].[year] = 2002 )
                         AND ( [t0].[month] = 12 ) )
                        OR ( ( [t0].[year] = 2017 )
                             AND ( [t0].[month] = 1 ) ) )
                      OR ( ( ( [t0].[year] = 2017 )
                             AND ( [t0].[month] = 12 ) )
                            OR ( ( [t0].[year] = 2001 )
                                 AND ( [t0].[month] = 1 ) ) ) ) )
      GROUP  BY [t0].[year],
                [t0].[month]

      """


  val complexSQLServer: String =
    """
            SELECT TOP (1000001) [t0].[year],
                           [t0].[month],
                           Sum([t0].[total_amount]) AS [a0],
                           Sum([t0].[extra])        AS [a1],
                           Sum([t0].[fare_amount])  AS [a2],
                           Sum([t0].[tip_amount])   AS [a3],
                           Sum([t0].[tolls_amount]) AS [a4],
                           Sum([t0].[mta_tax])      AS [a5],
                           ([t0].[month])        AS [a6],
                           ([t0].[year])         AS [a7]
      FROM   (SELECT [t0].[fare_amount]                  AS [fare_amount],
                     [t0].[extra]                        AS [extra],
                     [t0].[mta_tax]                      AS [mta_tax],
                     [t0].[tip_amount]                   AS [tip_amount],
                     [t0].[tolls_amount]                 AS [tolls_amount],
                     [t0].[total_amount]                 AS [total_amount],
                     Year([t0].[tpep_dropoff_datetime])  AS [Year],
                     Month([t0].[tpep_dropoff_datetime]) AS [Month]
              FROM   ((SELECT [__table].[vendorid]              AS [vendorid],
                              [__table].[tpep_pickup_datetime]  AS
                              [tpep_pickup_datetime],
                              [__table].[tpep_dropoff_datetime] AS
                              [tpep_dropoff_datetime],
                              [__table].[passenger_count]       AS [passenger_count],
                              [__table].[trip_distance]         AS [trip_distance],
                              [__table].[ratecodeid]            AS [ratecodeid],
                              [__table].[store_and_fwd_flag]    AS [store_and_fwd_flag]
                              ,
                              [__table].[pulocationid]
                              AS [pulocationid],
                              [__table].[dolocationid]          AS [dolocationid],
                              [__table].[payment_type]          AS [payment_type],
                              [__table].[fare_amount]           AS [fare_amount],
                              [__table].[extra]                 AS [extra],
                              [__table].[mta_tax]               AS [mta_tax],
                              [__table].[tip_amount]            AS [tip_amount],
                              [__table].[tolls_amount]          AS [tolls_amount],
                              [__table].[improvement_surcharge] AS [improvement_surcharge],
                              [__table].[total_amount]          AS [total_amount],
                              [__table].[s_row_id]              AS [s_row_id],
                              [__table].[s_timestamp]           AS [s_timestamp],
                              [__table].[s_input_id]            AS [s_input_id]
                       FROM   #$table AS [__table])) AS [t0]) AS
             [t0]
      GROUP  BY [t0].[year],
                [t0].[month]
    """


  val insertStatement: SqlQuery = SQL(
    """
      INSERT INTO __results(
       server_size,
       query_id,
       table_size,
       duration,
       database_system)
       VALUES(
       {serverSize},
       {queryId},
       {tableSize},
       {duration},
       {jdbcDriverType}
       )""".stripMargin)

 /* val insertStatement: SqlQuery = SQL(
    """
      INSERT INTO __results(
       test_datetime,
       server_size,
       query_id,
       table_size,
       duration,
       database_system)
       VALUES(
       {simpleSmallStartTime},
       {serverSize},
       1,
       'S',
       {simpleSmallDuration},
       {jdbcDriverType}
       ),
       (
       {normalSmallStartTime},
       {serverSize},
       2,
       'S',
       {normalSmallDuration},
       {jdbcDriverType}
       ),
       (
       {complexSmallStartTime},
       {serverSize},
       3,
       'S',
       {complexSmallDuration},
       {jdbcDriverType}
       ),
       (
       {simpleMediumStartTime},
       {serverSize},
       1,
       'M',
       {simpleMediumDuration},
       {jdbcDriverType}
       ),
       (
       {normalMediumStartTime},
       {serverSize},
       2,
       'M',
       {normalMediumDuration},
       {jdbcDriverType}
       ),
       (
       {complexMediumStartTime},
       {serverSize},
       3,
       'M',
       {complexMediumDuration},
       {jdbcDriverType}
       ),
       (
       {simpleLargeStartTime},
       {serverSize},
       1,
       'L',
       {simpleLargeDuration},
       {jdbcDriverType}
       ),
       (
       {normalLargeStartTime},
       {serverSize},
       2,
       'L',
       {normalLargeDuration},
       {jdbcDriverType}
       ),
       (
       {complexLargeStartTime},
       {serverSize},
       3,
       'L',
       {complexLargeDuration},
       {jdbcDriverType}
       ),
    """.stripMargin)
*/
}
