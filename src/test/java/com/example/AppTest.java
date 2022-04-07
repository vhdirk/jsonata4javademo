package com.example;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.api.jsonata4java.Expression;
import com.api.jsonata4java.expressions.ParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void shouldRun() throws ParseException, IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        final Expression expression = new Expression("$map(*, $multiplyBy(2))");
        expression.assign("$multiplyBy", "function($multiplier) { function($value, $index, $array) { $value * $multiplier } }");

        final JsonNode jsonObj = mapper.readTree("[0, 1, 2, 3, 4, 5]");
        final JsonNode result = expression.evaluate(jsonObj);

        assertEquals("[0, 2, 4, 6, 8, 10]", result.toString());

    }
}
