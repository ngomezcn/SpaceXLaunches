package com.example.spacexlaunches.models.Rocket

data class RocketModel(
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val diameter: Diameter,
    val engines: Engines,
    //val first_flight: String,
    // val first_stage: FirstStage,
    //val flickr_images: List<String>,
    val height: Height,
    val id: String,
    val landing_legs: LandingLegs,
    val mass: Mass,
    val name: String,
    val payload_weights: List<PayloadWeight>,
    //val second_stage: SecondStage,
    val stages: Float,
    val success_rate_pct: Float,
    val type: String,
    val wikipedia: String
)

data class Diameter(
    val feet: Double,
    val meters: Double
)

data class Engines(
    val engine_loss_max: Float,
    val isp: Isp,
    val layout: String,
    val number: Float,
    val propellant_1: String,
    val propellant_2: String,
    val thrust_sea_level: ThrustSeaLevel,
    val thrust_to_weight: Float,
    val thrust_vacuum: ThrustVacuum,
    val type: String,
    val version: String
)

/*data class FirstStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Double,
    val reusable: Boolean,
    val thrust_sea_level: ThrustSeaLevelX,
    val thrust_vacuum: ThrustVacuumX
)*/

data class Height(
    val feet: Float,
    val meters: Double
)

data class LandingLegs(
    val material: Any,
    val number: Float
)

data class Mass(
    val kg: Float,
    val lb: Float
)

data class PayloadWeight(
    val id: String,
    val kg: Float,
    val lb: Float,
    val name: String
)

data class SecondStage(
    val burn_time_sec: Float,
    val engines: Float,
    val fuel_amount_tons: Double,
    val payloads: Payloads,
    val reusable: Boolean,
    val thrust: Thrust
)

data class Isp(
    val sea_level: Float,
    val vacuum: Float
)

data class ThrustSeaLevel(
    val kN: Float,
    val lbf: Float
)

data class ThrustVacuum(
    val kN: Float,
    val lbf: Float
)

data class Payloads(
    val composite_fairing: CompositeFairing,
    val option_1: String
)

data class Thrust(
    val kN: Float,
    val lbf: Float
)

data class CompositeFairing(
    val diameter: Diameter,
    val height: HeightX
)

data class HeightX(
    val feet: Double,
    val meters: Double
)