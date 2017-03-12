package com.hazza.recipe



/**
  * Created by hazzacheng on 17-3-11.
  */

trait SimpleFood {
  object Pear extends Food("Pear")
  def allFoods = List(Apple, Orange, Cream, Sugar)
  def allCategories = Nil
}

trait SimpleRecipes {
  this: SimpleFood =>
  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Pear),
    "Stir it all together"
  )

  def allRecipes = List(FruitSalad)
}


object SimpleDatabase extends Database with SimpleFood with SimpleRecipes {

  override def foodNamed(name: String): Option[Food] =
    allFoods.find(_.name == name)

  override def allRecipes: List[Recipe] = List(FruitSalad)

  case class FoodCategory(name: String, foods: List[Food])

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar))
  )



  def main(args: Array[String]): Unit = {
    val apple = SimpleDatabase.foodNamed("Apple").get
    println(SimpleBrowser.recipesUsing(apple))
  }
}

