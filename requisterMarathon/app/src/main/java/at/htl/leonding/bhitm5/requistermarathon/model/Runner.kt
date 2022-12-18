package at.htl.leonding.bhitm5.requistermarathon.model

class Runner : java.io.Serializable{
    var firstName: String
    var lastName: String
    var age: Int

    constructor(firstName: String, lastName: String, age: Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    override fun toString(): String {
        return this.firstName + "," + this.lastName + "," + this.age.toString()
    }
}