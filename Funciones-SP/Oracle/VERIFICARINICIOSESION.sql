create or replace NONEDITIONABLE PROCEDURE verificariniciosesion(esadmin IN INTEGER, aliass IN VARCHAR2, cursor OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN cursor FOR
    SELECT contrasenna
    FROM usuario
    WHERE alias = aliass;

END;