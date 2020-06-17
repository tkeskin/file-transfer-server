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
import tr.com.tkeskin.activemq.Producer;
import tr.com.tkeskin.exception.BadRequestException;
import tr.com.tkeskin.exception.ErrorDetails;
import tr.com.tkeskin.models.JobDestinationList;
import tr.com.tkeskin.models.JobList;
import tr.com.tkeskin.models.JobsDto;
import tr.com.tkeskin.service.JobService;
import tr.com.tkeskin.util.Const;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Tag(name = "job", description = ".")
@RestController
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    Producer producer;

    /**
     * job - list
     *
     * @return .
     */
    @Operation(
            summary = "Find all job",
            description = "Find all job",
            tags = "Job"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = JobList.class)
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
    @GetMapping(value = Const.Request.JOB, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> all() {

        try {
            return ResponseEntity.ok(jobService.findAll());
        } catch (Exception e) {
            throw new BadRequestException(e.getLocalizedMessage());
        }

    }

    /**
     * job - save
     *
     * @return .
     */
    @Operation(
            summary = "Create new job",
            description = "Create a new job",
            tags = "Job"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = JobsDto.class)
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
                            description = "already exists",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @PostMapping(value = Const.Request.JOB, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveJob(@Valid @RequestBody JobsDto jobsDto) {

        try {
            return ResponseEntity.ok(jobService.saveJob(jobsDto));
        } catch (Exception exp) {
            throw new BadRequestException(exp.getMessage());
        }

    }

    @Operation(
            summary = "Delete a job",
            description = "Delete a job",
            tags = "Job"
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
                            description = "not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping(value = Const.Request.DELETE_JOB, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteJob(@PathVariable(value = "id") UUID id) throws Exception {

        try {
            return ResponseEntity.ok(jobService.deleteJob(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Operation(
            summary = "Detail for job",
            description = "Detail",
            tags = "Job"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = JobDestinationList.class)
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
                            description = "not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping(value = Const.Request.DETAIL_JOB, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobDestination(@PathVariable(value = "id") UUID id) throws Exception {

        try {
            return ResponseEntity.ok(jobService.findByJobId(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Operation(
            summary = "Query for job",
            description = "Query",
            tags = "Job"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = JobDestinationList.class)
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
                            description = "not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping(value = Const.Request.QUERY_JOB, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryJob(@PathVariable(value = "createdById") UUID createdById)
            throws Exception {

        try {
            return ResponseEntity.ok(jobService.findBycreatedById(createdById));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}