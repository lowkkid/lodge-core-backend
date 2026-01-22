package com.github.lowkkid.lodgecore.user.controller;

import com.github.lowkkid.lodgecore.user.model.UserDTO;
import com.github.lowkkid.lodgecore.user.model.UserRole;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

/**
 * API interface for user management operations.
 * All endpoints require admin authority.
 */
@Tag(name = "Users", description = "API for user management (admin only)")
public interface UserApi {

    @Operation(
            summary = "Create new employee",
            description = "Creates a new employee user account with the provided username and password. "
                    + "The password will be securely hashed before storage. "
                    + "Only administrators can create employee accounts."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee successfully created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing username or password)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have admin authority",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict - Employee with this username already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Void> createEmployee(
            @Parameter(description = "Employee credentials (username and password)")
            UsernameAndPassword usernameAndPassword
    );

    @Operation(
            summary = "Delete employee",
            description = "Deletes an existing employee user account by ID. "
                    + "Only employee accounts can be deleted; attempting to delete an admin account will fail. "
                    + "Only administrators can delete employee accounts."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Employee successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have admin authority or attempted to delete non-employee",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found with the specified ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Void> deleteEmployee(
            @Parameter(
                    description = "Unique identifier of the employee to delete",
                    required = true,
                    example = "550e8400-e29b-41d4-a716-446655440000"
            )
            UUID id
    );

    @Operation(
            summary = "Get all users",
            description = "Retrieves a paginated list of users with optional filtering by role. "
                    + "Results can be sorted by any user field. "
                    + "Only administrators can access the user list."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved paginated list of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTOPage.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have admin authority",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Page<UserDTO>> getUsers(
            @Parameter(
                    description = "Filter users by role. If not specified, returns all users.",
                    example = "EMPLOYEE"
            )
            UserRole role,

            @Parameter(
                    description = "Page number (1-indexed)",
                    example = "1"
            )
            Integer pageNumber,

            @Parameter(
                    description = "Number of items per page",
                    example = "10"
            )
            Integer pageSize,

            @Parameter(
                    description = "Field to sort by",
                    example = "createdAt"
            )
            String sortField,

            @Parameter(
                    description = "Sort direction (ASC or DESC)",
                    example = "DESC"
            )
            Sort.Direction sortDirection
    );

    /**
     * Schema class for OpenAPI documentation of paginated UserDTO response.
     */
    @Schema(description = "Paginated response containing user data")
    class UserDTOPage {
        @Schema(description = "List of users in current page")
        public List<UserDTO> content;

        @Schema(description = "Total number of elements across all pages", example = "50")
        public long totalElements;

        @Schema(description = "Total number of pages", example = "5")
        public int totalPages;

        @Schema(description = "Current page number (0-indexed)", example = "0")
        public int number;

        @Schema(description = "Number of elements in current page", example = "10")
        public int size;

        @Schema(description = "Whether this is the first page")
        public boolean first;

        @Schema(description = "Whether this is the last page")
        public boolean last;

        @Schema(description = "Whether the page is empty")
        public boolean empty;
    }
}
