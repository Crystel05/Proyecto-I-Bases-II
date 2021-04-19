CREATE OR REPLACE FUNCTION triggerSubasta()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$

DECLARE
	montoViejo DECIMAL(19,4);

BEGIN

	SELECT MAX(oferta)-- INTO montoViejo
    FROM puja 
    WHERE "subastaId" = NEW."subastaId";

    IF montoViejo < NEW.oferta OR montoViejo IS NULL THEN
        UPDATE subasta 
        SET "mejorMonto" = NEW.oferta
        WHERE "ID" = NEW."subastaId";
    ELSE
        UPDATE subasta 
        SET "mejorMonto" = montoViejo
        WHERE "ID" = NEW."subastaId";
    END IF;
	RETURN NEW;

END;
$BODY$


CREATE TRIGGER triggerMejorSubasta
BEFORE INSERT ON puja
FOR EACH ROW
EXECUTE PROCEDURE triggerSubasta();


