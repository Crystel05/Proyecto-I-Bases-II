CREATE OR REPLACE FUNCTION public.registrarusuario(
	esadmin boolean,
	alias character varying,
	contrasenna character varying,
	cedula character varying,
	nombre character varying,
	apellidos character varying,
	direccion character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
	cedulaIgual int;
	idUsuario int;
	participanteId int;
BEGIN
	cedulaIgual := (SELECT "ID" FROM usuario WHERE usuario.docIdent = cedula);
	
	IF cedulaIgual IS NOT null
	THEN
		RETURN 0;
	ELSE
		INSERT INTO public.usuario("Alias", nombreapellidos, contrasenna, docident, direccion)
		VALUES ("alias", CONCAT(nombre, ' ', apellidos), contrasenna, cedula, direccion);
		idUsuario := (SELECT "ID" FROM usuario WHERE usuario.docIdent = cedula);
		IF esAdmin
		THEN
			INSERT INTO public.administrador("usuarioId")
			VALUES(idUsuario);
		ELSE
			INSERT INTO public.participante("usuarioId")
			VALUES(idUsuario);
			participanteId := (SELECT "ID" FROM participante WHERE "usuarioId" = idUsuario);
			
			INSERT INTO vendedor("participanteId")
			VALUES(participanteId);
			
			INSERT INTO comprador("participanteId")
			VALUES(participanteId);
		END IF;
		RETURN 1;
		
	END IF;
END;
$BODY$;

	