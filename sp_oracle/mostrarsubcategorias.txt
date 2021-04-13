create or replace NONEDITIONABLE PROCEDURE mostSubCategorias(categoria in varchar2, cursor OUT SYS_REFCURSOR)
IS

BEGIN

    OPEN CURSOR FOR SELECT subcategoria.nombre
    FROM subcategoria INNER JOIN categoria
    ON categoria.id = subcategoria.categoriaid
    WHERE categoria.nombre = categoria;

END;