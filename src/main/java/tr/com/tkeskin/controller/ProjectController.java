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
import tr.com.tkeskin.models.ProjectDto;
import tr.com.tkeskin.models.ProjectList;
import tr.com.tkeskin.service.ProjectService;
import tr.com.tkeskin.util.Const;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Tag(name = "project", description = ".")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * project - list
     *
     * @return .
     */
    @Operation(
            summary = "Find all project",
            description = "Find all project",
            tags = "Project"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ProjectList.class)
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
    @GetMapping(value = Const.Request.PROJECT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> all() {

        return ResponseEntity.ok(projectService.findAll());
    }

    @Operation(
            summary = "Delete a project",
            description = "Delete a project",
            tags = "Project"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful",
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
    @GetMapping(value = Const.Request.DELETE_PROJECT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") UUID id) {

        try {
            return ResponseEntity.ok(projectService.deleteProject(id));
        } catch (Exception exp) {
            throw new BadRequestException(exp.getLocalizedMessage());
        }
    }

    /**
     * project - save && update
     *
     * @return .
     */
    @Operation(
            summary = "Create new project",
            description = "Create a new project",
            tags = "Project"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful",
                            content = @Content(
                                    schema = @Schema(implementation = ProjectDto.class)
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
                            description = "Project already exists",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @PostMapping(value = Const.Request.PROJECT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProject(@Valid @RequestBody ProjectDto projectDto) {

        try {
            return ResponseEntity.ok(projectService.saveProject(projectDto));
        } catch (Exception exp) {
            throw new BadRequestException(exp.getLocalizedMessage());
        }

    }

}
