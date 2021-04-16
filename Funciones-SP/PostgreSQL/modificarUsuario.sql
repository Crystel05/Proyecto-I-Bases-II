CREATE OR REPLACE FUNCTION public.modificarUsuario(cedVieja varchar, nomAp varchar, aliNuevo varchar, contra varchar, cedula varchar, direcc varchar)
RETURNS INTEGER
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
BEGIN
	IF cedVieja = cedula
	THEN
		UPDATE usuario
		SET "Alias" = aliNuevo,
		nombreapellidos = nomAp,
		docident = cedula,
		direccion = direcc,
		contrasenna = contra
		WHERE docident = cedVieja;
		RETURN 1;
	ELSE
		IF (SELECT "ID" FROM usuario WHERE docident = cedula) IS NULL
		THEN
			
			UPDATE usuario
			SET "Alias" = aliNuevo,
			nombreapellidos = nomAp,
			docident = cedula,
			direccion = direcc,
			contrasenna = contra
			WHERE docident = cedVieja;
			RETURN 1;
		ELSE
			RETURN 0;
		END IF;
	END IF;

END;
$BODY$;

