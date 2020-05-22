package tr.com.aa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.aa.dal.entity.FtpServerEntity;
import tr.com.aa.models.FtpServerDto;

@Mapper(componentModel = "spring")
public interface FtpServerMapper {

  FtpServerMapper INSTANCE = Mappers.getMapper(FtpServerMapper.class);

  FtpServerEntity toFtpServerEntity(FtpServerDto ftpServerDto);

  FtpServerDto toFtpServerDto(FtpServerEntity ftpServerEntity);

  //entityList ver , dtoList al.
  List<FtpServerDto> toFtpServerDtoList(List<FtpServerEntity> ftpServerEntities);
}