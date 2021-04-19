create or replace NONEDITIONABLE PROCEDURE crearSubasta(
nombreItem IN VARCHAR2,
detallesItem IN VARCHAR2,
imagen IN VARCHAR2,
subcat IN VARCHAR2,
montoIni IN DECIMAL,
fechaFinal IN TIMESTAMP,
detalles IN VARCHAR2,
aliasVendedor IN VARCHAR2,
contraVendedor IN varchar2,
montoMinimo decimal,
retorno OUT INTEGER)
IS

    subcatId INTEGER;
    iteId INTEGER;
    vendId INTEGER;
    verificacion INTEGER;

BEGIN

    SELECT ID INTO subcatId FROM subcategoria WHERE nombre = subcat;

    INSERT INTO ITEM(descripcion,
    nombre,
    subcategoriaid,
    imagen)
    VALUES(
    detallesItem,
    nombreItem,
    subcatId,
    imagen);

    SELECT vendedor.ID INTO vendId
    FROM vendedor
    INNER JOIN participante ON vendedor.participanteId= participante.ID
    INNER JOIN usuario ON participante.usuarioId = usuario.ID
    WHERE usuario.alias = aliasVendedor AND usuario.contrasenna = contraVendedor;

    SELECT ID INTO iteId FROM item WHERE nombre = nombreItem;

    IF montominimo = 0 THEN
        INSERT INTO SUBASTA(
        precioInicial,
        detallesEntrega,
        fechaInicio,
        fechaFinal,
        itemId,
        vendedorId)
        VALUES (
        montoIni,
        detalles,
        SYSDATE,
        fechaFinal,
        iteId,
        vendId);

    ELSE
        INSERT INTO SUBASTA(
        precioInicial,
        detallesEntrega,
        fechaInicio,
        fechaFinal,
        itemId,
        vendedorId,
        agregadoMinimo)
        VALUES (
        montoIni,
        detalles,
        SYSDATE,
        fechaFinal,
        iteId,
        vendId,
        montoMinimo);
    END IF;
END;