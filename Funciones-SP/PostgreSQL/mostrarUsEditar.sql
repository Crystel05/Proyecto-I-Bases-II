CREATE OR REPLACE FUNCTION public.mostrarUsuariosEditar(esAdmin boolean)
    RETURNS TABLE(cedula character varying, nombre varchar) 
    LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
	
	IF(esAdmin)
	THEN
	
		RETURN QUERY
		SELECT usuario.docident, usuario.nombreapellidos
		FROM administrador 
		INNER JOIN usuario ON administrador."usuarioId" = usuario."ID";
		
	ELSE
		
		RETURN QUERY
		SELECT usuario.docident, usuario.nombreapellidos
		FROM participante 
		INNER JOIN usuario ON participante."usuarioId" = usuario."ID";
	
	END IF;
	

END;
$BODY$;

