create or replace NONEDITIONABLE PROCEDURE devolvercedula(alis varchar2, contra varchar2, cursor OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN cursor FOR SELECT docidentidad FROM usuario WHERE alias = alis AND contrasenna = contra;

END;