create or replace NONEDITIONABLE PROCEDURE nombreVendedor(cedula IN varchar2, cursor OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN cursor FOR SELECT nombreapellidos FROM usuario WHERE  docidentidad = cedula;

END;