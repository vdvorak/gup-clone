package ua.com.gup.rent.editor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyEditorSupport;

public abstract class CreateRentObjectDTOPropertyEditorSupport extends PropertyEditorSupport{

    protected final ObjectMapper mapper = new ObjectMapper();
}
