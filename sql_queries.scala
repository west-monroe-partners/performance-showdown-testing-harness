package wmp.taxi_blog

import anorm.{SQL, SqlQuery}

object sql_queries {

  val simpleSQL: String =
    """
      SELECT Sum(a0) AS a0
      FROM   ( (SELECT t0.year              AS c28,
                     t0.month             AS c29,
                     Sum(t0.total_amount) AS a0
              FROM   (SELECT t0.total_amount                 AS total_amount,
                             date_part('year',t0.tpep_dropoff_datetime)  AS Year,
                             date_part('month',t0.tpep_dropoff_datetime) AS Month
                      FROM   ((SELECT _Table.vendorid              AS vendorid,
                                      _Table.tpep_pickup_datetime  AS
                                      tpep_pickup_datetime,
                                      _Table.tpep_dropoff_datetime AS
                                      tpep_dropoff_datetime,
                                      _Table.passenger_count       AS
                                      passenger_count,
                                      _Table.trip_distance         AS
                                      trip_distance,
                                      _Table.ratecodeid            AS ratecodeid
                                      ,
      _Table.store_and_fwd_flag    AS
      store_and_fwd_flag,
      _Table.pulocationid          AS pulocationid,
      _Table.dolocationid          AS dolocationid,
      _Table.payment_type          AS payment_type,
      _Table.fare_amount           AS fare_amount,
      _Table.extra                 AS extra,
      _Table.mta_tax               AS mta_tax,
      _Table.tip_amount            AS tip_amount,
      _Table.tolls_amount          AS tolls_amount,
      _Table.improvement_surcharge AS
      improvement_surcharge,
      _Table.total_amount          AS total_amount,
      _Table.s_row_id              AS s_row_id
      FROM   #$table AS _Table)) AS t0) AS
      t0
      GROUP  BY t0.year,
      t0.month) AS basetable0
      INNER JOIN ((SELECT 2018 AS c28,
      7    AS c29)
      UNION ALL
      (SELECT 2018 AS c28,
      1    AS c29)
      UNION ALL
      (SELECT 2018 AS c28,
      2    AS c29)) AS semijoin1
      ON ( ( semijoin1.c28 = basetable0.c28 )
      AND ( semijoin1.c29 = basetable0.c29 ) ) );

      """

  val normalSQL: String =
    """SELECT  t0.year,
                           t0.month,
                           Sum(t0.total_amount) AS a0
      FROM   (SELECT t0.total_amount                 AS total_amount,
      						date_part('year',t0.tpep_dropoff_datetime)  AS Year,
                             date_part('month',t0.tpep_dropoff_datetime) AS Month
      FROM   ((SELECT _Table.vendorid              AS vendorid,
                              _Table.tpep_pickup_datetime  AS
                              tpep_pickup_datetime,
                              _Table.tpep_dropoff_datetime AS
                              tpep_dropoff_datetime,
                              _Table.passenger_count       AS passenger_count,
                              _Table.trip_distance         AS trip_distance,
                              _Table.ratecodeid            AS ratecodeid,
                              _Table.store_and_fwd_flag    AS store_and_fwd_flag
                              ,
                              _Table.pulocationid
                              AS pulocationid,
                              _Table.dolocationid          AS dolocationid,
                              _Table.payment_type          AS payment_type,
                              _Table.fare_amount           AS fare_amount,
                              _Table.extra                 AS extra,
                              _Table.mta_tax               AS mta_tax,
                              _Table.tip_amount            AS tip_amount,
                              _Table.tolls_amount          AS tolls_amount,
                              _Table.improvement_surcharge AS
                              improvement_surcharge,
                              _Table.total_amount          AS total_amount,
                              _Table.s_row_id              AS s_row_id
                       FROM   #$table AS _Table)) AS t0) AS
             t0
      WHERE  ( ( ( ( ( ( t0.year = 2008 )
                       AND ( t0.month = 12 ) )
                      OR ( ( t0.year = 2018 )
                           AND ( t0.month = 7 ) ) )
                    OR ( ( ( t0.year = 2018 )
                           AND ( t0.month = 1 ) )
                          OR ( ( t0.year = 2018 )
                               AND ( t0.month = 2 ) ) ) )
                  OR ( ( ( t0.year = 2009 )
                         AND ( t0.month = 1 ) )
                        OR ( ( t0.year = 2003 )
                             AND ( t0.month = 1 ) ) ) )
                OR ( ( ( ( t0.year = 2002 )
                         AND ( t0.month = 12 ) )
                        OR ( ( t0.year = 2017 )
                             AND ( t0.month = 1 ) ) )
                      OR ( ( ( t0.year = 2017 )
                             AND ( t0.month = 12 ) )
                            OR ( ( t0.year = 2001 )
                                 AND ( t0.month = 1 ) ) ) ) )
      GROUP  BY t0.year,
                t0.month
      LIMIT 1000001;
      """


  val complexSQL: String =
    """
            SELECT  t0.year,
                                 t0.month,
                                 Sum(t0.total_amount) AS a0,
                                 Sum(t0.extra)        AS a1,
                                 Sum(t0.fare_amount)  AS a2,
                                 Sum(t0.tip_amount)   AS a3,
                                 Sum(t0.tolls_amount) AS a4,
                                 Sum(t0.mta_tax)      AS a5,
                                 t0.month        AS a6,
                                 t0.year        AS a7
            FROM   (SELECT t0.fare_amount                  AS fare_amount,
                           t0.extra                        AS extra,
                           t0.mta_tax                      AS mta_tax,
                           t0.tip_amount                   AS tip_amount,
                           t0.tolls_amount                 AS tolls_amount,
                           t0.total_amount                 AS total_amount,
            						date_part('year',t0.tpep_dropoff_datetime)  AS Year,
                                   date_part('month',t0.tpep_dropoff_datetime) AS Month
                    FROM   ((SELECT _Table.vendorid              AS vendorid,
                                    _Table.tpep_pickup_datetime  AS
                                    tpep_pickup_datetime,
                                    _Table.tpep_dropoff_datetime AS
                                    tpep_dropoff_datetime,
                                    _Table.passenger_count       AS passenger_count,
                                    _Table.trip_distance         AS trip_distance,
                                    _Table.ratecodeid            AS ratecodeid,
                                    _Table.store_and_fwd_flag    AS store_and_fwd_flag
                                    ,
                                    _Table.pulocationid
                                    AS pulocationid,
                                    _Table.dolocationid          AS dolocationid,
                                    _Table.payment_type          AS payment_type,
                                    _Table.fare_amount           AS fare_amount,
                                    _Table.extra                 AS extra,
                                    _Table.mta_tax               AS mta_tax,
                                    _Table.tip_amount            AS tip_amount,
                                    _Table.tolls_amount          AS tolls_amount,
                                    _Table.improvement_surcharge AS
                                    improvement_surcharge,
                                    _Table.total_amount          AS total_amount,
                                    _Table.s_row_id              AS s_row_id
                             FROM   #$table AS _Table)) AS t0) AS
                   t0
            GROUP  BY t0.year,
                      t0.month
            LIMIT 1000001;
    """


}
