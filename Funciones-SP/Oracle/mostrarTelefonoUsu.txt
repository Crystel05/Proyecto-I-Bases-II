create or replace NONEDITIONABLE PROCEDURE mostrartelefonosUsu (docIdent IN varchar2, cursor  OUT SYS_REFCURSOR)
IS

    usuId INTEGER;

BEGIN

    SELECT ID Into usuId
    From usuario where
    docidentidad = docident;

    OPEN CURSOR FOR 
    SELECT 
    numero
    FROM telefono
    WHERE usuarioid = usuid;

END ;