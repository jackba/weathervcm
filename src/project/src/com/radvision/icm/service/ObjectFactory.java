
package com.radvision.icm.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.radvision.icm.service package. 
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

    private final static QName _AuthUserResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "authUserResponse");
    private final static QName _GetUsersResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "getUsersResponse");
    private final static QName _SearchUsers_QNAME = new QName("http://radvision.com/icm/service/userservice", "searchUsers");
    private final static QName _UserNotExistedException_QNAME = new QName("http://radvision.com/icm/service/userservice", "UserNotExistedException");
    private final static QName _SearchUsersByLoginId_QNAME = new QName("http://radvision.com/icm/service/userservice", "searchUsersByLoginId");
    private final static QName _AuthUser_QNAME = new QName("http://radvision.com/icm/service/userservice", "authUser");
    private final static QName _DeleteUsers_QNAME = new QName("http://radvision.com/icm/service/userservice", "deleteUsers");
    private final static QName _SetUsers_QNAME = new QName("http://radvision.com/icm/service/userservice", "setUsers");
    private final static QName _SearchUsersResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "searchUsersResponse");
    private final static QName _SetUsersResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "setUsersResponse");
    private final static QName _GetUserByLoginIdResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "getUserByLoginIdResponse");
    private final static QName _GetUserByLoginId_QNAME = new QName("http://radvision.com/icm/service/userservice", "getUserByLoginId");
    private final static QName _DeleteUsersResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "deleteUsersResponse");
    private final static QName _GetUsers_QNAME = new QName("http://radvision.com/icm/service/userservice", "getUsers");
    private final static QName _SearchUsersByLoginIdResponse_QNAME = new QName("http://radvision.com/icm/service/userservice", "searchUsersByLoginIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.radvision.icm.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserByLoginIdResponse }
     * 
     */
    public GetUserByLoginIdResponse createGetUserByLoginIdResponse() {
        return new GetUserByLoginIdResponse();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link SearchUsersByLoginIdResponse }
     * 
     */
    public SearchUsersByLoginIdResponse createSearchUsersByLoginIdResponse() {
        return new SearchUsersByLoginIdResponse();
    }

    /**
     * Create an instance of {@link AuthUserResponse }
     * 
     */
    public AuthUserResponse createAuthUserResponse() {
        return new AuthUserResponse();
    }

    /**
     * Create an instance of {@link UserResult }
     * 
     */
    public UserResult createUserResult() {
        return new UserResult();
    }

    /**
     * Create an instance of {@link AuthUser }
     * 
     */
    public AuthUser createAuthUser() {
        return new AuthUser();
    }

    /**
     * Create an instance of {@link SearchUsersResponse }
     * 
     */
    public SearchUsersResponse createSearchUsersResponse() {
        return new SearchUsersResponse();
    }

    /**
     * Create an instance of {@link UserNotExistedException }
     * 
     */
    public UserNotExistedException createUserNotExistedException() {
        return new UserNotExistedException();
    }

    /**
     * Create an instance of {@link DeleteUsersResponse }
     * 
     */
    public DeleteUsersResponse createDeleteUsersResponse() {
        return new DeleteUsersResponse();
    }

    /**
     * Create an instance of {@link DeleteUsers }
     * 
     */
    public DeleteUsers createDeleteUsers() {
        return new DeleteUsers();
    }

    /**
     * Create an instance of {@link SetUsers }
     * 
     */
    public SetUsers createSetUsers() {
        return new SetUsers();
    }

    /**
     * Create an instance of {@link UserSearchConditon }
     * 
     */
    public UserSearchConditon createUserSearchConditon() {
        return new UserSearchConditon();
    }

    /**
     * Create an instance of {@link SetUsersResponse }
     * 
     */
    public SetUsersResponse createSetUsersResponse() {
        return new SetUsersResponse();
    }

    /**
     * Create an instance of {@link SearchUsersByLoginId }
     * 
     */
    public SearchUsersByLoginId createSearchUsersByLoginId() {
        return new SearchUsersByLoginId();
    }

    /**
     * Create an instance of {@link SearchUsers }
     * 
     */
    public SearchUsers createSearchUsers() {
        return new SearchUsers();
    }

    /**
     * Create an instance of {@link GetUserByLoginId }
     * 
     */
    public GetUserByLoginId createGetUserByLoginId() {
        return new GetUserByLoginId();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "authUserResponse")
    public JAXBElement<AuthUserResponse> createAuthUserResponse(AuthUserResponse value) {
        return new JAXBElement<AuthUserResponse>(_AuthUserResponse_QNAME, AuthUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "searchUsers")
    public JAXBElement<SearchUsers> createSearchUsers(SearchUsers value) {
        return new JAXBElement<SearchUsers>(_SearchUsers_QNAME, SearchUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotExistedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "UserNotExistedException")
    public JAXBElement<UserNotExistedException> createUserNotExistedException(UserNotExistedException value) {
        return new JAXBElement<UserNotExistedException>(_UserNotExistedException_QNAME, UserNotExistedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersByLoginId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "searchUsersByLoginId")
    public JAXBElement<SearchUsersByLoginId> createSearchUsersByLoginId(SearchUsersByLoginId value) {
        return new JAXBElement<SearchUsersByLoginId>(_SearchUsersByLoginId_QNAME, SearchUsersByLoginId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "authUser")
    public JAXBElement<AuthUser> createAuthUser(AuthUser value) {
        return new JAXBElement<AuthUser>(_AuthUser_QNAME, AuthUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "deleteUsers")
    public JAXBElement<DeleteUsers> createDeleteUsers(DeleteUsers value) {
        return new JAXBElement<DeleteUsers>(_DeleteUsers_QNAME, DeleteUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "setUsers")
    public JAXBElement<SetUsers> createSetUsers(SetUsers value) {
        return new JAXBElement<SetUsers>(_SetUsers_QNAME, SetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "searchUsersResponse")
    public JAXBElement<SearchUsersResponse> createSearchUsersResponse(SearchUsersResponse value) {
        return new JAXBElement<SearchUsersResponse>(_SearchUsersResponse_QNAME, SearchUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "setUsersResponse")
    public JAXBElement<SetUsersResponse> createSetUsersResponse(SetUsersResponse value) {
        return new JAXBElement<SetUsersResponse>(_SetUsersResponse_QNAME, SetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByLoginIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "getUserByLoginIdResponse")
    public JAXBElement<GetUserByLoginIdResponse> createGetUserByLoginIdResponse(GetUserByLoginIdResponse value) {
        return new JAXBElement<GetUserByLoginIdResponse>(_GetUserByLoginIdResponse_QNAME, GetUserByLoginIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByLoginId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "getUserByLoginId")
    public JAXBElement<GetUserByLoginId> createGetUserByLoginId(GetUserByLoginId value) {
        return new JAXBElement<GetUserByLoginId>(_GetUserByLoginId_QNAME, GetUserByLoginId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "deleteUsersResponse")
    public JAXBElement<DeleteUsersResponse> createDeleteUsersResponse(DeleteUsersResponse value) {
        return new JAXBElement<DeleteUsersResponse>(_DeleteUsersResponse_QNAME, DeleteUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersByLoginIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://radvision.com/icm/service/userservice", name = "searchUsersByLoginIdResponse")
    public JAXBElement<SearchUsersByLoginIdResponse> createSearchUsersByLoginIdResponse(SearchUsersByLoginIdResponse value) {
        return new JAXBElement<SearchUsersByLoginIdResponse>(_SearchUsersByLoginIdResponse_QNAME, SearchUsersByLoginIdResponse.class, null, value);
    }

}
