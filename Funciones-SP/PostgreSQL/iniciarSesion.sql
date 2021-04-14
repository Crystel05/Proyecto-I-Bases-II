CREATE OR REPLACE FUNCTION public.verificariniciosesion(
	esadmin boolean,
	alias character varying,
	contra character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
	usuarioID int;
	tipoID int;
BEGIN
	usuarioID := (SELECT "ID" FROM usuario WHERE "Alias" = "alias" and contrasenna = contra); 
	IF usuarioID IS NOT NULL
	THEN 
		IF esAdmin
		THEN
			tipoID := (SELECT "ID" FROM administrador WHERE administrador."usuarioId"= usuarioID);
		ELSE
			tipoID := (SELECT "ID" FROM participante WHERE participante."usuarioId" = usuarioID);
		END IF;
		IF tipoID IS NOT NULL
		THEN 
			RETURN 1;
		ELSE
			RETURN 0;
		END IF;
	ELSE
		RETURN 0;
	END IF;

END;
$BODY$;