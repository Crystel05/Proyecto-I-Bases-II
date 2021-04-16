create or replace NONEDITIONABLE PROCEDURE mostSubPorSubcat(subcategoria IN varchar2, cursor OUT SYS_REFCURSOR)
IS

    subCatId INTEGER;

BEGIN

    SELECT ID INTO subCatId FROM subCategoria WHERE nombre = subcategoria;

    OPEN cursor FOR SELECT  nombre, fechafinal, montoactual
    FROM subasta
    INNER JOIN item on subasta.itemid = item.id
    WHERE item.subcategoriaid = subcatid AND activo = 1; 

END;