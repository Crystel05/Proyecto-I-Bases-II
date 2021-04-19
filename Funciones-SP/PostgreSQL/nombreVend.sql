CREATE OR REPLACE FUNCTION public.nombreVendedor(docIden varchar)
    RETURNS varchar
    LANGUAGE 'plpgsql'

AS $BODY$
	DECLARE 
		nombreApVend varchar;
BEGIN
		nombreApVend := (SELECT nombreapellidos FROM usuario WHERE docident = docIden);
		RETURN nombreApVend;

END;
$BODY$;

SELECT * FROM nombreVendedor('000000')