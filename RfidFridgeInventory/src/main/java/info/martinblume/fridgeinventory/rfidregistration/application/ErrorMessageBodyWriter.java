package info.martinblume.fridgeinventory.rfidregistration.application;

import com.google.common.base.Charsets;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.dropwizard.jersey.validation.ValidationErrorMessage;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Created by mblume on 24.12.14.
 */
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class ErrorMessageBodyWriter implements MessageBodyWriter<ErrorMessage> {

    private static final Logger LOG = Logger.getLogger(ErrorMessageBodyWriter.class.getName());

    @Override
    public boolean isWriteable(
            Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType)
    {
        return ValidationErrorMessage.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(
            ErrorMessage t,
            Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType)
    {
        return -1;
    }

    @Override
    public void writeTo(
            ErrorMessage t,
            Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException
    {
        String message = t.getMessage();
        entityStream.write(message.getBytes(Charsets.UTF_8));
        LOG.info(message);
    }
}
