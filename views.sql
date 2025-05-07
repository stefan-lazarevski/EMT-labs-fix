CREATE MATERIALIZED VIEW housing_count_by_host AS
SELECT a.id                                   AS host_id,
       CONCAT(a.name, ' ', a.surname) AS full_name,
       COUNT(b.id)                            AS housing_count
FROM host a
         LEFT JOIN
     housing b ON b.host = a.id
GROUP BY a.id, a.name, a.surname;

CREATE MATERIALIZED VIEW host_count_by_country AS
SELECT c.id        AS country_id,
       c.name      AS country_name,
       COUNT(a.id) AS host_count
FROM country c
         LEFT JOIN
     host a ON a.country_id = c.id
GROUP BY c.id, c.name;