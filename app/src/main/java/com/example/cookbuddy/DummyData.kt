package com.example.cookbuddy

val allRecipes = listOf(
    Recipe(
        1, "Egg Salad", "Western", 333, 30, 13, 2, 216_000, R.drawable.egg_salad,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        2, "Pasta Carbonara", "Western", 450, 25, 15, 40, 180_000, R.drawable.pasta_carbonara,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        3, "Iced Coffee", "Drinks", 120, 0, 1, 25, 140_000, R.drawable.iced_coffee,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        4, "Chocolate Cake", "Dessert", 500, 20, 6, 60, 200_000, R.drawable.chocolate_cake,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        5, "Fruit Tart", "Dessert", 280, 12, 3, 35, 90_000, R.drawable.fruit_tart,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        6, "Ramen", "Western", 600, 22, 25, 55, 300_000, R.drawable.ramen,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        7, "Pizza", "Western", 700, 30, 20, 60, 250_000, R.drawable.pizza,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        8, "Matcha Latte", "Drinks", 180, 5, 6, 25, 150_000, R.drawable.matcha_latte,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        9, "Tiramisu", "Dessert", 350, 18, 7, 40, 130_000, R.drawable.tiramisu,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        10, "Nasi Goreng", "Asian", 500, 20, 15, 60, 210_000, R.drawable.nasi_goreng,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        11, "Chicken Teriyaki", "Asian", 420, 18, 28, 35, 175_000, R.drawable.chicken_teriyaki,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        12, "Avocado Toast with Egg", "etc", 320, 18, 12, 30, 110_000, R.drawable.avocado_toast_egg,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    ),
    Recipe(
        13, "Instant Cup Noodles Remix", "etc", 350, 14, 7, 45, 85_000, R.drawable.instant_noodle,
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
        reviews = listOf(
            Review("Key of Jack Princess", R.drawable.avatar1, 5.0, "Perfect recipe! My family loved it.", "1 week ago"),
            Review("Foodie Lover", R.drawable.avatar2, 4.5, "Great taste and easy to follow.", "3 days ago")
        )
    )
)
