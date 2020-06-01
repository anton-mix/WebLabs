
package lab3;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lab3 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Upload_QNAME = new QName("http://Lab3/", "Upload");
    private final static QName _Insert_QNAME = new QName("http://Lab3/", "Insert");
    private final static QName _Update_QNAME = new QName("http://Lab3/", "Update");
    private final static QName _Load_QNAME = new QName("http://Lab3/", "Load");
    private final static QName _Delete_QNAME = new QName("http://Lab3/", "Delete");
    private final static QName _LoadResponse_QNAME = new QName("http://Lab3/", "LoadResponse");
    private final static QName _InsertResponse_QNAME = new QName("http://Lab3/", "InsertResponse");
    private final static QName _OutOfRangeException_QNAME = new QName("http://Lab3/", "OutOfRangeException");
    private final static QName _Select_QNAME = new QName("http://Lab3/", "Select");
    private final static QName _MissFormatException_QNAME = new QName("http://Lab3/", "MissFormatException");
    private final static QName _SelectResponse_QNAME = new QName("http://Lab3/", "SelectResponse");
    private final static QName _DeleteResponse_QNAME = new QName("http://Lab3/", "DeleteResponse");
    private final static QName _UploadResponse_QNAME = new QName("http://Lab3/", "UploadResponse");
    private final static QName _UpdateResponse_QNAME = new QName("http://Lab3/", "UpdateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lab3
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link LoadResponse }
     * 
     */
    public LoadResponse createLoadResponse() {
        return new LoadResponse();
    }

    /**
     * Create an instance of {@link InsertResponse }
     * 
     */
    public InsertResponse createInsertResponse() {
        return new InsertResponse();
    }

    /**
     * Create an instance of {@link Load }
     * 
     */
    public Load createLoad() {
        return new Load();
    }

    /**
     * Create an instance of {@link Upload }
     * 
     */
    public Upload createUpload() {
        return new Upload();
    }

    /**
     * Create an instance of {@link Insert }
     * 
     */
    public Insert createInsert() {
        return new Insert();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UploadResponse }
     * 
     */
    public UploadResponse createUploadResponse() {
        return new UploadResponse();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link Select }
     * 
     */
    public Select createSelect() {
        return new Select();
    }

    /**
     * Create an instance of {@link SoapServiceFault }
     * 
     */
    public SoapServiceFault createSoapServiceFault() {
        return new SoapServiceFault();
    }

    /**
     * Create an instance of {@link SelectResponse }
     * 
     */
    public SelectResponse createSelectResponse() {
        return new SelectResponse();
    }

    /**
     * Create an instance of {@link SqlObject }
     * 
     */
    public SqlObject createSqlObject() {
        return new SqlObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Upload")
    public JAXBElement<Upload> createUpload(Upload value) {
        return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Insert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Insert")
    public JAXBElement<Insert> createInsert(Insert value) {
        return new JAXBElement<Insert>(_Insert_QNAME, Insert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Load }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Load")
    public JAXBElement<Load> createLoad(Load value) {
        return new JAXBElement<Load>(_Load_QNAME, Load.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "LoadResponse")
    public JAXBElement<LoadResponse> createLoadResponse(LoadResponse value) {
        return new JAXBElement<LoadResponse>(_LoadResponse_QNAME, LoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "InsertResponse")
    public JAXBElement<InsertResponse> createInsertResponse(InsertResponse value) {
        return new JAXBElement<InsertResponse>(_InsertResponse_QNAME, InsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "OutOfRangeException")
    public JAXBElement<SoapServiceFault> createOutOfRangeException(SoapServiceFault value) {
        return new JAXBElement<SoapServiceFault>(_OutOfRangeException_QNAME, SoapServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Select }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "Select")
    public JAXBElement<Select> createSelect(Select value) {
        return new JAXBElement<Select>(_Select_QNAME, Select.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "MissFormatException")
    public JAXBElement<SoapServiceFault> createMissFormatException(SoapServiceFault value) {
        return new JAXBElement<SoapServiceFault>(_MissFormatException_QNAME, SoapServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "SelectResponse")
    public JAXBElement<SelectResponse> createSelectResponse(SelectResponse value) {
        return new JAXBElement<SelectResponse>(_SelectResponse_QNAME, SelectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "DeleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "UploadResponse")
    public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
        return new JAXBElement<UploadResponse>(_UploadResponse_QNAME, UploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Lab3/", name = "UpdateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

}
