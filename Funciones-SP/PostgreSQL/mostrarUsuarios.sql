CREATE OR REPLACE FUNCTION public.mostrarUsuarios(
	)
    RETURNS TABLE(cedula character varying, nombre varchar) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	RETURN QUERY
	SELECT usuario.docident, usuario.nombreapellidos
	FROM participante 
	INNER JOIN usuario ON participante."usuarioId" = usuario."ID";

END;
$BODY$;



