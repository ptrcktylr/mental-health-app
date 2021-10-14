export class Sentiment {

    //NEED TO CHANGE ACCORDING TO BACKEND JSON REQUIREMENTS
    constructor(

        public author:number,
        public header:String,
        public body:String,
        public tags:String,
        public sentimentScore:number

    ){}

}
