export class StringUtils{
    static removeLineBreaks(text:string):string{
        return text.trim().replace(/(\r\n|\n|\r)/gm, "")
    }
}