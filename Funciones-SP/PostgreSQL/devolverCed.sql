CREATE OR REPLACE FUNCTION public.devolvercedula(alis varchar, contra varchar)
    RETURNS varchar
    LANGUAGE 'plpgsql'

AS $BODY$

DECLARE 
	docidentidad integer;

BEGIN

	docidentidad := (SELECT docident FROM usuario WHERE "Alias" = alis AND contrasenna = contra);
	
	RETURN docidentidad;
	
END;
$BODY$;
