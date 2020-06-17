package tr.com.tkeskin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.tkeskin.dal.entity.FtpServerEntity;
import tr.com.tkeskin.models.FtpServerDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FtpServerMapper {

    FtpServerMapper INSTANCE = Mappers.getMapper(FtpServerMapper.class);

    FtpServerEntity toFtpServerEntity(FtpServerDto ftpServerDto);

    FtpServerDto toFtpServerDto(FtpServerEntity ftpServerEntity);

    //entityList ver , dtoList al.
    List<FtpServerDto> toFtpServerDtoList(List<FtpServerEntity> ftpServerEntities);
}