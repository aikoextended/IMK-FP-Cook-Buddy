package com.example.cookbuddy

val defaultReviews = listOf(
    Review(
        userName = "Team 4",
        avatarRes = R.drawable.avatar1,
        rating = 5.0,
        comment = "Great recipe! added some extra sesame salad dressing and it turned out delicious.",
        reviewImageRes = R.drawable.pic1_review,
        timeAgo = "just now"
    ),
    Review(
        userName = "Key of Jack Princess",
        avatarRes = R.drawable.avatar2,
        rating = 5.0,
        comment = "Perfect recipe! The instruction were clear and the result was amazing. My family loved it!",
        reviewImageRes = R.drawable.pic2_review,
        timeAgo = "1 week ago"
    )
)

val allRecipes = listOf(
    Recipe(
        id = 1,
        title = "Egg Salad",
        category = "Western",
        calories = 333,
        fat = 30,
        protein = 13,
        carbs = 2,
        likes = 216_000,
        imageRes = R.drawable.egg_salad,
        time = 15,
        servings = 2,
        ingredients = listOf(
            "4 boiled eggs",
            "2 tbsp mayonnaise",
            "1 tsp mustard",
            "Salt and pepper to taste",
            "1 stalk celery, diced",
            "1/4 onion, finely chopped"
        ),
        instructions = listOf(
            "Peel and chop the boiled eggs.",
            "Mix with mayonnaise, mustard, celery, and onion.",
            "Season with salt and pepper.",
            "Serve chilled."
        ),
        instructionImages = listOf(
            R.drawable.step1_egg_salad,
            R.drawable.step2_egg_salad,
            R.drawable.step3_egg_salad,
            null
        ),
        reviews = listOf(
            Review("Team 4", R.drawable.avatar1, 5.0, "Great recipe! added some extra sesame salad dressing and it turned out delicious.", R.drawable.pic1_review, "just now"),
            Review("Key of Jack Princess", R.drawable.avatar2, 5.0, "Perfect recipe! The instructions were clear and the result was amazing. My family loved it!", R.drawable.pic2_review, "1 week ago")
        )
    ),

    // Resep ID 2–13: Semua instructionImages = null
    Recipe(
        id = 2,
        title = "Pasta Carbonara",
        category = "Western",
        calories = 450,
        fat = 25,
        protein = 15,
        carbs = 40,
        likes = 300_000,
        imageRes = R.drawable.pasta_carbonara,
        time = 25,
        servings = 2,
        ingredients = listOf(
            "200g spaghetti",
            "2 egg yolks",
            "50g grated parmesan",
            "100g pancetta or bacon",
            "1 clove garlic, minced",
            "Salt and pepper"
        ),
        instructions = listOf(
            "Cook spaghetti until al dente.",
            "Cook pancetta with garlic until crispy.",
            "Mix egg yolks and cheese in a bowl.",
            "Combine hot pasta with pancetta and egg mixture off heat.",
            "Stir quickly and season."
        ),
        instructionImages = List(5) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 3,
        title = "Iced Coffee",
        category = "Drinks",
        calories = 120,
        fat = 0,
        protein = 1,
        carbs = 25,
        likes = 90_000,
        imageRes = R.drawable.iced_coffee,
        time = 10,
        servings = 1,
        ingredients = listOf(
            "1 shot espresso or 100ml strong brewed coffee",
            "100ml cold milk (optional)",
            "1 tsp sugar or syrup (optional)",
            "Ice cubes"
        ),
        instructions = listOf(
            "Brew coffee and let it cool slightly.",
            "Fill a glass with ice.",
            "Pour coffee over ice.",
            "Add milk/syrup as desired and stir."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 4,
        title = "Chocolate Cake",
        category = "Dessert",
        calories = 500,
        fat = 20,
        protein = 6,
        carbs = 60,
        likes = 400_000,
        imageRes = R.drawable.chocolate_cake,
        time = 60,
        servings = 8,
        ingredients = listOf(
            "200g flour",
            "200g sugar",
            "100g cocoa powder",
            "2 eggs",
            "150ml milk",
            "100g butter",
            "1 tsp baking powder"
        ),
        instructions = listOf(
            "Mix dry ingredients in a bowl.",
            "Add eggs, milk, and melted butter.",
            "Pour into a greased baking tin.",
            "Bake at 180°C for 35–40 mins.",
            "Let cool and serve."
        ),
        instructionImages = List(5) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 5,
        title = "Fruit Tart",
        category = "Dessert",
        calories = 280,
        fat = 12,
        protein = 3,
        carbs = 35,
        likes = 80_000,
        imageRes = R.drawable.fruit_tart,
        time = 40,
        servings = 6,
        ingredients = listOf(
            "1 tart shell",
            "200ml pastry cream",
            "Assorted fruits (strawberry, kiwi, blueberry)",
            "1 tbsp fruit glaze (optional)"
        ),
        instructions = listOf(
            "Bake the tart shell if not pre-baked.",
            "Fill with pastry cream.",
            "Arrange fruits on top.",
            "Brush with glaze.",
            "Chill before serving."
        ),
        instructionImages = List(5) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 6,
        title = "Ramen",
        category = "Western",
        calories = 600,
        fat = 22,
        protein = 25,
        carbs = 55,
        likes = 70_000,
        imageRes = R.drawable.ramen,
        time = 30,
        servings = 2,
        ingredients = listOf(
            "2 packs ramen noodles",
            "500ml chicken broth",
            "2 boiled eggs",
            "Sliced chashu pork or chicken",
            "Green onions",
            "Soy sauce",
            "Nori sheets"
        ),
        instructions = listOf(
            "Boil broth with soy sauce.",
            "Cook ramen noodles.",
            "Add noodles to bowl, pour broth.",
            "Top with egg, meat, green onions, and nori."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 7,
        title = "Pizza",
        category = "Western",
        calories = 700,
        fat = 30,
        protein = 20,
        carbs = 60,
        likes = 130_000,
        imageRes = R.drawable.pizza,
        time = 45,
        servings = 4,
        ingredients = listOf(
            "Pizza dough",
            "100g tomato sauce",
            "150g mozzarella cheese",
            "Toppings: pepperoni, mushrooms, etc.",
            "Oregano"
        ),
        instructions = listOf(
            "Spread sauce on rolled dough.",
            "Add cheese and toppings.",
            "Sprinkle oregano.",
            "Bake at 220°C for 15–20 mins."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 8,
        title = "Matcha Latte",
        category = "Drinks",
        calories = 180,
        fat = 5,
        protein = 6,
        carbs = 25,
        likes = 150_000,
        imageRes = R.drawable.matcha_latte,
        time = 5,
        servings = 1,
        ingredients = listOf(
            "1 tsp matcha powder",
            "50ml hot water",
            "150ml warm milk",
            "1 tsp honey or sweetener"
        ),
        instructions = listOf(
            "Whisk matcha with hot water.",
            "Heat and froth milk.",
            "Pour milk into matcha.",
            "Stir in honey."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 9,
        title = "Tiramisu",
        category = "Dessert",
        calories = 350,
        fat = 18,
        protein = 7,
        carbs = 40,
        likes = 135_000,
        imageRes = R.drawable.tiramisu,
        time = 240,
        servings = 6,
        ingredients = listOf(
            "200g ladyfingers",
            "250g mascarpone",
            "3 egg yolks",
            "100ml espresso",
            "50g sugar",
            "Cocoa powder"
        ),
        instructions = listOf(
            "Whisk yolks with sugar, then add mascarpone.",
            "Dip ladyfingers in coffee and layer in dish.",
            "Add mascarpone layer.",
            "Repeat layers, top with cocoa.",
            "Chill for 3–4 hours."
        ),
        instructionImages = List(5) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 10,
        title = "Nasi Goreng",
        category = "Asian",
        calories = 500,
        fat = 20,
        protein = 15,
        carbs = 60,
        likes = 265_000,
        imageRes = R.drawable.nasi_goreng,
        time = 25,
        servings = 2,
        ingredients = listOf(
            "2 plates of cooked rice (cold)",
            "2 cloves garlic, minced",
            "2 shallots, sliced",
            "1 egg",
            "50g chicken or shrimp (optional)",
            "1 tbsp kecap manis (sweet soy sauce)",
            "1 tsp soy sauce",
            "Salt and pepper to taste",
            "Vegetable oil for frying"
        ),
        instructions = listOf(
            "Heat oil, sauté garlic and shallots until fragrant.",
            "Add chicken/shrimp and cook until done.",
            "Push to the side and scramble egg in the pan.",
            "Add rice, sauces, salt and pepper. Stir-fry evenly.",
            "Serve hot with cucumber or fried egg on top."
        ),
        instructionImages = List(5) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 11,
        title = "Chicken Teriyaki",
        category = "Asian",
        calories = 420,
        fat = 18,
        protein = 28,
        carbs = 35,
        likes = 50_000,
        imageRes = R.drawable.chicken_teriyaki,
        time = 30,
        servings = 2,
        ingredients = listOf(
            "2 boneless chicken thighs",
            "2 tbsp soy sauce",
            "2 tbsp mirin",
            "1 tbsp sake (optional)",
            "1 tbsp sugar",
            "1 tsp grated ginger",
            "1 tsp vegetable oil",
            "Sesame seeds and chopped scallions for garnish"
        ),
        instructions = listOf(
            "Mix soy sauce, mirin, sake, sugar, and ginger in a bowl.",
            "Heat oil in a pan and cook chicken until browned on both sides.",
            "Add the sauce and simmer until thickened and chicken is glazed.",
            "Slice chicken and serve with rice, garnished with sesame and scallions."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 12,
        title = "Avocado Toast with Egg",
        category = "etc",
        calories = 320,
        fat = 18,
        protein = 12,
        carbs = 30,
        likes = 60_000,
        imageRes = R.drawable.avocado_toast_egg,
        time = 10,
        servings = 1,
        ingredients = listOf(
            "1 slice whole grain bread",
            "1/2 ripe avocado",
            "1 egg (poached or fried)",
            "Salt and pepper",
            "Chili flakes (optional)",
            "Lemon juice"
        ),
        instructions = listOf(
            "Toast the bread until golden.",
            "Mash avocado with salt, pepper, and lemon juice.",
            "Spread avocado on toast.",
            "Top with egg and sprinkle chili flakes if desired."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    ),

    Recipe(
        id = 13,
        title = "Instant Cup Noodles",
        category = "etc",
        calories = 350,
        fat = 14,
        protein = 7,
        carbs = 45,
        likes = 330_000,
        imageRes = R.drawable.instant_noodle,
        time = 5,
        servings = 1,
        ingredients = listOf(
            "1 cup instant noodles",
            "1 tsp sesame oil",
            "1 boiled egg, halved",
            "A few slices of cooked chicken or tofu",
            "Chopped green onions",
            "Chili flakes (optional)"
        ),
        instructions = listOf(
            "Prepare noodles according to package instructions.",
            "Add sesame oil and mix well.",
            "Top with egg, chicken/tofu, green onions, and chili flakes.",
            "Serve hot."
        ),
        instructionImages = List(4) { null },
        reviews = defaultReviews
    )
)

//val defaultReviews = listOf(
//    Review("Team 4", R.drawable.avatar1, 5.0, "Great recipe! added some extra sesame salad dressing and it turned out delicious.", R.drawable.pic1_review, "just now"),
//    Review("Key of Jack Princess", R.drawable.avatar2, 5.0, "Perfect recipe! The instruction were clear and the result was amazing. My family loved it!", R.drawable.pic2_review, "1 week ago")
//)
