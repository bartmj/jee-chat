package pl.training.chat.payments.adapters.rest.api;

import lombok.Setter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("payments")
@Setter
public class MessageController {

    @Inject
    private PaymentService paymentService;
    @Inject
    private RestPaymentMapper paymentMapper;
    @Context
    private UriInfo uriInfo;

    // @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response process(@Valid PaymentRequestDto paymentRequestDto) {
        var paymentRequest = paymentMapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = paymentMapper.toDto(payment);
        return Response.created(getLocation(paymentDto.id))
                .entity(paymentDto)
                .build();
    }

    // @Produces(BinaryMapper.MEDIA_TYPE)
    @GET
    @Path("{id:\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}}")
    public Response getById(@PathParam("id") String id) {
        var payment = paymentService.getById(id);
        var paymentDto = paymentMapper.toDto(payment);
        return Response.ok(paymentDto).build();
    }

    @GET
    @Path("confirmed")
    public Response getByStatus(@QueryParam("pageSize") @DefaultValue("5") int pageSize,
                                @QueryParam("pageNumber") @DefaultValue("0") int pageNumber) {
        var page = new Page(pageNumber, pageSize);
        var resultPage = paymentService.getByStatus(PaymentStatus.CONFIRMED, page);
        var resultPageDto = paymentMapper.toDto(resultPage);
        return Response.ok(resultPageDto).build();
    }

    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }

}
