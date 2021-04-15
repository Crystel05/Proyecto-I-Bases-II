create or replace NONEDITIONABLE PROCEDURE mostrarTipoTelefono(cursor  OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN CURSOR FOR 
    SELECT 
    tipo
    FROM tipotelefono;

END ;