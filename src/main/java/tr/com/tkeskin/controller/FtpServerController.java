package tr.com.tkeskin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.tkeskin.exception.BadRequestException;
import tr.com.tkeskin.exception.ErrorDetails;
import tr.com.tkeskin.models.FtpServerDto;
import tr.com.tkeskin.models.FtpServerList;
import tr.com.tkeskin.service.FtpServerService;
import tr.com.tkeskin.util.Const;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Tag(name = "ftp", description = ".")
@RestController
public class FtpServerController {

    @Autowired
    FtpServerService ftpServerService;

    /**
     * ftp server - list
     *
     * @return .
     */
    @Operation(
            summary = "Find all ftp server",
            description = "Find all ftp server",
            tags = "FTP Server"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = FtpServerList.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping(value = Const.Request.FTP_SERVER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> all() {

        return ResponseEntity.ok(ftpServerService.findAll());
    }

    /**
     * ftp server - save && update
     *
     * @return .
     */
    @Operation(
            summary = "Create new ftp server",
            description = "Create a new ftp server",
            tags = "FTP Server"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = FtpServerDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Ftp server already exists",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @PostMapping(value = Const.Request.FTP_SERVER, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFtpServer(@Valid @RequestBody FtpServerDto ftpServerDto) {

        try {
            return ResponseEntity.ok(ftpServerService.saveFtpServer(ftpServerDto));
        } catch (Exception exp) {
            throw new BadRequestException(exp.getLocalizedMessage());
        }

    }

    @Operation(
            summary = "Delete a ftp server",
            description = "Delete a ftp server",
            tags = "FTP Server"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = FtpServerDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ftp server not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping(value = Const.Request.DELETE_FTP_SERVER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFtpServer(@PathVariable(value = "id") UUID id) {

        try {
            return ResponseEntity.ok(ftpServerService.deleteFtpServer(id));
        } catch (Exception exp) {
            throw new BadRequestException(exp.getLocalizedMessage());
        }
    }

    @Operation(
            summary = "Check ftp server connection",
            description = "Check ftp server connection",
            tags = "FTP Server"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Check ftp server connection not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @PostMapping(value = Const.Request.TEST_CONNECTION, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testConnection(@RequestBody FtpServerDto ftpServerDto) {

        try {
            return ResponseEntity.ok(ftpServerService.testConnection(ftpServerDto));
        } catch (Exception e) {
            throw new BadRequestException(e.getLocalizedMessage());
        }
    }
}