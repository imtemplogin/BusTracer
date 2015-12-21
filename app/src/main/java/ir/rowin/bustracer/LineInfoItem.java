package ir.rowin.bustracer;

public class LineInfoItem {
    public Integer _lineNumber;
    public Integer _lineID;
    public Integer _linePriceWithoutCard;
    public Integer _linePriceWithCard;
    public Integer _allStaionInLine;
    public Integer _allBusesInLine;
    public Integer _realTimeBusesInLine;
    public Integer _busesBetweenTime;
    public String _startTerminal;
    public String _endTerminal;


    public LineInfoItem(
            Integer lineNumber,
            Integer lineID,
            Integer linePriceWithoutCard,
            Integer linePriceWithCard,
            Integer allStaionInLine,
            Integer allBusesInLine,
            Integer realTimeBusesInLine,
            String startTerminal,
            String endTerminal,
            Integer busesBetweenTime) {
        _lineNumber = lineNumber;
        _lineID = lineID;
        _linePriceWithCard = linePriceWithCard;
        _linePriceWithoutCard = linePriceWithoutCard;
        _allStaionInLine = allStaionInLine;
        _allBusesInLine = allBusesInLine;
        _realTimeBusesInLine = realTimeBusesInLine;
        _startTerminal = startTerminal;
        _endTerminal = endTerminal;
        _busesBetweenTime = busesBetweenTime;
    }
}
