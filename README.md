# Performance Showdown Whitepaper Testing Harness
Performance Showdown Whitepaper Testing Harness

In order to speed up testing process and mitigate the influence of human error, hardware variance, and other external factors, a simple testing harness was created to automatically execute all queries and record their execution times. After running each query, results were inserted directly to a SQL Server table created for results tracking. This table recorded the DB type, DB size, query type, table size, and total execution time of each query. Tests were performed for all combinations, except for Brytlyt, which was unable to fit the medium sized table in memory on S/M instance sizes and unable to fit the large dataset on any of our tested configurations.
