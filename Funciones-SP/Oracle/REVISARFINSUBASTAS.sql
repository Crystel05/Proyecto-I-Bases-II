create or replace NONEDITIONABLE PROCEDURE revisarFinSubastas
 IS
    
    CURSOR tabla IS SELECT * FROM subasta WHERE activo = 1;
    --i INTEGER;
    --fechaFila TIMESTAMP(9);
 BEGIN

    FOR linea IN tabla LOOP
        IF CURRENT_TIMESTAMP > linea.fechaFinal THEN
            UPDATE subasta 
            SET activo = 0
            WHERE ID = linea.ID;
        END IF;
    END LOOP; 
 END;
