

Spring6WebMethod(args) ::= <<

    /*
    * <summary>
    * <description>
    * </summary>
    * @param <parameterName> <description>
    * @return <description>
    */
    @<verb>Mapping(<path>)
    public <methodReturnType> <operationId>((<httpParameterType> <parameterType> <parameterName>, Map<String, String> queryParameters)*)
>>

Spring6WebInterface(args) ::= <<
//package

//imports

public interface <tag>Client {

    <methods:{m|Spring6WebMethod();}>

}
>>