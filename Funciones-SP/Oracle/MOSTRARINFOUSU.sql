create or replace NONEDITIONABLE PROCEDURE mostrarInfoUsu (docIden IN varchar2, cursor  OUT SYS_REFCURSOR)
IS

BEGIN

OPEN CURSOR FOR 
SELECT 
contrasenna,
nombreapellidos,
docIdentidad,
direccion,
alias
FROM usuario
WHERE docIdentidad = docIden;

END ;