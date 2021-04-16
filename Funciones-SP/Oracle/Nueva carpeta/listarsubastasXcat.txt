create or replace NONEDITIONABLE PROCEDURE mostSubPorCat(categoria IN varchar2, cursor OUT SYS_REFCURSOR)
IS

    catId INTEGER;

BEGIN

    SELECT ID INTO catId FROM categoria WHERE nombre = categoria;

    OPEN cursor FOR SELECT  nombre, fechafinal, montoactual
    FROM subasta
    INNER JOIN item on subasta.itemid = item.id
    WHERE item.subcategoriaid IN 
    (SELECT ID FROM subCategoria WHERE categoriaId = catId) AND activo = 1; 

END;