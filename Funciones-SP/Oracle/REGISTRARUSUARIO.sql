create or replace NONEDITIONABLE Procedure registrarusuario( esadmin IN INTEGER, alias IN VARCHAR2, contra IN VARCHAR2, cedula IN VARCHAR2, nombre IN VARCHAR2, apellido IN VARCHAR2, direccion IN VARCHAR2, retorno OUT INTEGER)
    
IS
	idUsuario INTEGER;
    idParticipante INTEGER;
    nombreCompleto VARCHAR2(50) :=  CONCAT((CONCAT( nombre, ' ')),apellido);
    cantidadCedulas INTEGER;
BEGIN

    SELECT COUNT(*) INTO cantidadCedulas FROM usuario WHERE docIdentidad = cedula;
    INSERT INTO usuario(alias, nombreapellidos, contrasenna, docidentidad, direccion)
    VALUES (alias, nombreCompleto, contra, cedula, direccion);

    SELECT ID INTO idUsuario FROM usuario WHERE docIdentidad = cedula;
    IF cantidadCedulas <= 0 THEN
        IF esAdmin = 1
        THEN
            INSERT INTO administrador(usuarioId)
            VALUES(idUsuario);
        ELSE
            INSERT INTO participante(usuarioId)
            VALUES(idUsuario);

            SELECT participante.ID INTO idParticipante
            FROM participante where usuarioId = idUsuario;

            INSERT INTO vendedor(participanteId)
            VALUES(idParticipante);

            INSERT INTO comprador(participanteId)
            VALUES(idParticipante);
        retorno := 0;
        END IF;
    ELSE
        retorno := 1;
    END IF;
END;