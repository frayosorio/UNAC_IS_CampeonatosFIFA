CREATE OR REPLACE FUNCTION fObtenerTablaPosiciones(idGrupoTabla BIGINT)
RETURNS TABLE(
    Id INTEGER,
    Pais VARCHAR(100),
    PJ INTEGER,
    PG INTEGER,
    PE INTEGER,
    PP INTEGER,
    GF INTEGER,
    GC INTEGER,
    Puntos INTEGER
) 
LANGUAGE SQL AS $$
    SELECT 
        P.id,
        P.Pais,
        COUNT(E.id) AS PJ,
        SUM(CASE WHEN (E.idPais1 = P.id AND E.goles1 > E.goles2) 
                  OR (E.idPais2 = P.id AND E.goles2 > E.goles1) 
                 THEN 1 ELSE 0 END) AS PG,
        SUM(CASE WHEN E.goles1 = E.goles2 THEN 1 ELSE 0 END) AS PE,
        SUM(CASE WHEN (E.idPais1 = P.id AND E.goles1 < E.goles2) 
                  OR (E.idPais2 = P.id AND E.goles2 < E.goles1) 
                 THEN 1 ELSE 0 END) AS PP,
        SUM(CASE WHEN E.idPais1 = P.id THEN E.goles1 ELSE E.goles2 END) AS GF,
        SUM(CASE WHEN E.idPais1 = P.id THEN E.goles2 ELSE E.goles1 END) AS GC,
        SUM(
            CASE 
                WHEN (E.idPais1 = P.id AND E.goles1 > E.goles2) 
                     OR (E.idPais2 = P.id AND E.goles2 > E.goles1) THEN 3
                WHEN E.goles1 = E.goles2 THEN 1
                ELSE 0 
            END
        ) AS Puntos
    FROM Pais P
    INNER JOIN GrupoPais GP ON P.id = GP.idPais
    INNER JOIN Grupo G ON GP.idGrupo = G.id
    INNER JOIN Encuentro E ON (E.idPais1 = P.id OR E.idPais2 = P.id)
    WHERE GP.idGrupo = idGrupoTabla
        AND E.idCampeonato = G.idCampeonato
        AND E.goles1 IS NOT NULL
        AND E.goles2 IS NOT NULL
    GROUP BY P.id, P.Pais
    ORDER BY 
        SUM(
            CASE 
                WHEN (E.idPais1 = P.id AND E.goles1 > E.goles2) 
                     OR (E.idPais2 = P.id AND E.goles2 > E.goles1) THEN 3
                WHEN E.goles1 = E.goles2 THEN 1
                ELSE 0 
            END
        ) DESC,
        (SUM(CASE WHEN E.idPais1 = P.id THEN E.goles1 ELSE E.goles2 END) - 
         SUM(CASE WHEN E.idPais1 = P.id THEN E.goles2 ELSE E.goles1 END)) DESC;
$$;
