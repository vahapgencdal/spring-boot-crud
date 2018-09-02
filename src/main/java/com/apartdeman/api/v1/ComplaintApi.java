package com.apartdeman.api.v1;

import com.apartdeman.api.BaseApi;
import com.apartdeman.api.Response;
import com.apartdeman.model.entity.Complaint;
import com.apartdeman.model.request.ComplaintRequest;
import com.apartdeman.model.service.ComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */

@Api(value = "Complaints",description = "Operations pertaining to Complaints in Apartdeman")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/complaints",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
@CrossOrigin(origins = "*")
public class ComplaintApi extends BaseApi {

    private final ComplaintService complaintService;

    @RequestMapping(path = "/active/{stId}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value="Return all active Complaints by status id", notes="This is a public API", response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any complaint by stId")
    })
    public ResponseEntity getActiveComplaintsByStId(@PathVariable("stId") Long stId){

    List<Complaint> complaints= complaintService.getActiveComplaintsByStId(stId);
        return ResponseEntity.ok().body(complaints);
   }

    @RequestMapping(path = "/deleted/{stId}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value="Return all deleted Complaints by status id", notes="This is a public API with admin right", response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any complaint by stId")
    })
    public ResponseEntity getDeletedComplaintsByStId(@PathVariable("stId") Long stId){

        List<Complaint> complaints = complaintService.getDeletedComplaintsByStId(stId);
        return ResponseEntity.ok().body(complaints);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value="Add a new Complaint with details", notes="This is a public API", response=Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new complaint has been added successfully"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "You havent permission to authorize this api")
    })
    public ResponseEntity save(
            @Valid @Size(max = 40, min = 1, message = "user id size 1-40") @RequestHeader(name = HEADER_USER_ID) Long userId,
            @Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token,
            @Valid @RequestBody ComplaintRequest complaint){
        Complaint complaintAdded=complaintService.save(complaint,userId);
        return ResponseEntity.ok().body(complaintAdded.getId());
    }


    @RequestMapping(path = "/update", method = RequestMethod.PUT,
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value="Add a new Complaint with details", notes="This is a public API", response=Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new complaint has been added successfully"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "You havent permission to authorize this api")
    })
    public ResponseEntity update(
            @Valid @Size(max = 40, min = 1, message = "user id size 1-40") @RequestHeader(name = HEADER_USER_ID) Long userId,
            @Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token,
            @Valid @RequestBody ComplaintRequest complaint){
        Complaint complaintUpdated=complaintService.update(complaint,userId);
        return ResponseEntity.ok().body(complaintUpdated.getId());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
            produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value="Return active Complaint by id", notes="This is a public API", response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any complaint by id")
    })
    public ResponseEntity get(@PathVariable("id") Long id){

        Complaint complaint = complaintService.getById(id);
        return new ResponseEntity(complaint, !StringUtils.isEmpty(complaint) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE,
            produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value="Soft Delete Complaint and return value of it", notes="This is a public API", response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any complaint by id")
    })
    public ResponseEntity delete(@PathVariable("id") Long id,
    @Valid @Size(max = 40, min = 1, message = "user id size 1-40") @RequestHeader(name = HEADER_USER_ID) Long userId,
    @Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token
                                 ){
        Complaint complaint=complaintService.deleteSoft(id,userId);
        return new ResponseEntity(complaint.getId()>0, StringUtils.isEmpty(complaint) ? HttpStatus.NO_CONTENT : HttpStatus.OK);

    }
}
