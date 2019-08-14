public class ParseFactory {
    public parse getParse(ParserType type){
        if (type == ParserType.JSON){
            return new JSONParse();
        }
        if (type == ParserType.XML){
            return new XMLParse();
        }
        return null;
    }
}
