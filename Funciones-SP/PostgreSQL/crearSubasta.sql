CREATE OR REPLACE FUNCTION public.crearSubasta(
nombreItem varchar,
detallesItem varchar,
imagen varchar,
subcat varchar,
montoIni numeric,
fechaFinal varchar,
detalles varchar,
aliasVendedor varchar,
contraVendedor varchar, 
montMin numeric)

RETURNS INTEGER
LANGUAGE 'plpgsql'
	
AS $BODY$

DECLARE
	subcatId integer;
    iteId integer;
    vendId integer;
    verificacion integer;
	
BEGIN
	
    subcatId := (SELECT "ID" FROM subcategoria WHERE nombre = subcat);

    INSERT INTO item(descripcion,
    nombre,
    "subcategoriaId",
    foto)
    VALUES(
    detallesItem,
    nombreItem,
    subcatId,
    imagen);

   	vendId := (SELECT DISTINCT vendedor."ID"
    FROM vendedor 
    INNER JOIN participante ON vendedor."participanteId" = participante."ID"
    INNER JOIN usuario ON participante."usuarioId" = usuario."ID"
    WHERE usuario."Alias" = aliasVendedor AND usuario.contrasenna = contraVendedor);

    iteId := (SELECT "ID" FROM item WHERE nombre = nombreItem);
	
	IF montMin = 0
	THEN
		
		INSERT INTO subasta(
		precioInicial,
		detallesentrega,
		fechainicio,
		fechafinal,
		itemid,
		vendedorid)
		VALUES (
		montoIni,
		detalles,
		now()::timestamp,
		TO_TIMESTAMP(
		fechaFinal,
		'YYYY-MM-DD HH:MI:SS'),
		iteId,
		vendId);
	ELSE
	
		INSERT INTO subasta(
		precioInicial,
		detallesentrega,
		fechainicio,
		fechafinal,
		itemid,
		vendedorid, 
		"minimoSistema")
		VALUES (
		montoIni,
		detalles,
		now()::timestamp,
		TO_TIMESTAMP(
		fechaFinal,
		'YYYY-MM-DD HH:MI:SS'),
		iteId,
		vendId,
		montMin);

	END IF;
    
	RETURN 1;

END;
$BODY$;

