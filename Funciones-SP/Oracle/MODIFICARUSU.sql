create or replace NONEDITIONABLE PROCEDURE modificarUsu(aliass IN VARCHAR2, nom IN varchar2,contra IN VARCHAR2, dociden IN VARCHAR2, docidenViejo IN VARCHAR2, direcc IN VARCHAR2, resultado OUT INTEGER)
IS

    cantCedulas INTEGER;

BEGIN

    IF dociden = docidenViejo THEN
        UPDATE USUARIO
        SET ALIAS = aliass,
        NOMBREAPELLIDOS = nom,
        DOCIDENTIDAD = dociden,
        DIRECCION = direcc,
        CONTRASENNA = contra
        WHERE docIdentidad = docidenViejo;
        resultado:= 0;

    ELSE
        SELECT COUNT(*) INTO cantCedulas FROM usuario WHERE docIdentidad = dociden;
        IF cantCedulas <= 0 THEN
            UPDATE USUARIO
            SET ALIAS = aliass,
            NOMBREAPELLIDOS = nom,
            DOCIDENTIDAD = dociden,
            DIRECCION = direcc,
            CONTRASENNA = contra
            WHERE docIdentidad = docidenViejo;
            resultado:= 0;

        ELSE
            resultado:= 1;

        END IF;

    END IF;

END ;