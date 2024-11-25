//https://youtu.be/0wmcCdoExbM?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

object AlgebraicDataTypes {
  //a way of structuring your data, not exclusive to functional programming

  //EG ADT
  //sealed means no other scala file can extend this trait
  sealed trait Weather // "Sum type"
  case object Sunny extends Weather
  case object Rainy extends Weather
  case object Windy extends Weather
  case object Cloudy extends Weather
  //case object gives us a range of features such as unapply, enabling us to use them in pattern matching

  //Weather = Sunny + Rainy + Windy + Cloudy == SUM Type

  //because this is a sealed trait, the compiler will warn that the pattern match is not exhaustive
  def feeling(weather: Weather): String = weather match {
    case Sunny => ":)"
    case Cloudy => ":|"
    case Rainy => ":("
  }

  case class WeatherForecastRequest(latitude: Double, longitude: Double) //case class also defines it's constructor. To instantiate need to pass a lat and long
  //equivalent to a function that is of the form (Double, Double) => WeatherForecastRequest
  //type WeatherForecastRequest = Double x Double (Product Type, usually implemented as case classes)

  //hybrid type:
  sealed trait WeatherForecastResponse // SUM type
  case class Valid(weather: Weather) extends WeatherForecastResponse //Product Type
  case class Invalid(error: String, description: String) extends WeatherForecastResponse //Product Type

  //advantages:
    //illegal states are NOT representable
    //ADTs are highly composable, can use within other ADTs for example
    //immutable data structures
    //usually store only data, not functionality => structure our code

  type NaiveWeather = String
  def naiveFeeling(weather: String) = weather match {
    case "sunny" => ":)"
    //other cases
  }

  naiveFeeling("45 degrees")
  //above method would fail with a match error. In a large codebase, this kind of invalid argument would crash entire application.
  //by using sealed traits and case objects etc, you would not be able to pass anything else other than those included.

  //complexity of an ADT = number of possible values of that ADT
  //goal: reduce complexity
  //explicitly allow for the possible values that you might want to support.

  sealed trait WeatherServerError
  case object NotAvailable extends WeatherServerError
  case object NotFound extends WeatherServerError
  //other cases

  //Instead of using string for an error, you would use this new type and these errors which would limit the complexity of this datatype
  //and greatly improve the testability of the application

}
