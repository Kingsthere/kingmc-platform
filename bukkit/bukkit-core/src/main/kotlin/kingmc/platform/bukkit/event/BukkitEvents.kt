@file:Suppress("unused", "DEPRECATION")

package kingmc.platform.bukkit.event

import org.bukkit.event.block.*
import org.bukkit.event.entity.*
import org.bukkit.event.entity.HorseJumpEvent
import org.bukkit.event.entity.EntityPortalEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.enchantment.*
import org.bukkit.event.hanging.*
import org.bukkit.event.inventory.*
import org.bukkit.event.player.*
import org.bukkit.event.raid.*
import org.bukkit.event.server.*
import org.bukkit.event.vehicle.*
import org.bukkit.event.weather.*
import org.bukkit.event.world.*

typealias PlayerJoin = PlayerJoinEvent
typealias PlayerQuit = PlayerQuitEvent

typealias PlayerDeath = PlayerDeathEvent
typealias AreaEffectCloudApply = AreaEffectCloudApplyEvent
typealias ArrowBodyCountChange = ArrowBodyCountChangeEvent
typealias BatToggleSleep = BatToggleSleepEvent
typealias CreatureSpawn = CreatureSpawnEvent
typealias CreeperPower = CreeperPowerEvent
typealias EnderDragonChangePhase = EnderDragonChangePhaseEvent
typealias EntityAirChange = EntityAirChangeEvent
typealias EntityBreakDoor = EntityBreakDoorEvent
typealias EntityBreed = EntityBreedEvent
typealias EntityChangeBlock = EntityChangeBlockEvent
typealias EntityCombustByBlock = EntityCombustByBlockEvent
typealias EntityCombustByEntity = EntityCombustByEntityEvent
typealias EntityCombust = EntityCombustEvent
typealias EntityCreatePortal = EntityCreatePortalEvent
typealias EntityDamageByBlock = EntityDamageByBlockEvent
typealias EntityDamageByEntity = EntityDamageByEntityEvent
typealias EntityDamage = EntityDamageEvent
typealias EntityDropItem = EntityDropItemEvent
typealias EntityEnterBlock = EntityEnterBlockEvent
typealias EntityEnterLoveMode = EntityEnterLoveModeEvent
typealias EntityExhaustion = EntityExhaustionEvent
typealias EntityExplode = EntityExplodeEvent
typealias EntityInteract = EntityInteractEvent
typealias EntityPickupItem = EntityPickupItemEvent
typealias EntityPlace = EntityPlaceEvent
typealias EntityPortalEnter = EntityPortalEnterEvent
typealias EntityPortalEvent = EntityPortalEvent
typealias EntityPortal = EntityPortalExitEvent
typealias EntityPortalExit = EntityPortalExitEvent
typealias EntityPoseChange = EntityPoseChangeEvent
typealias EntityPotionEffect = EntityPotionEffectEvent
typealias EntityRegainHealth = EntityRegainHealthEvent
typealias EntityResurrect = EntityResurrectEvent
typealias EntityShootBow = EntityShootBowEvent
typealias EntitySpawn = EntitySpawnEvent
typealias EntitySpellCast = EntitySpellCastEvent
typealias EntityTame = EntityTameEvent
typealias EntityTarget = EntityTargetEvent
typealias EntityTargetLivingEntity = EntityTargetLivingEntityEvent
typealias EntityTeleport = EntityTeleportEvent
typealias EntityToggleGlide = EntityToggleGlideEvent
typealias EntityToggleSwim = EntityToggleSwimEvent
typealias EntityTransform = EntityTransformEvent
typealias EntityUnleash = EntityUnleashEvent
typealias ExpBottle = ExpBottleEvent
typealias ExplosionPrime = ExplosionPrimeEvent
typealias FireworkExplode = FireworkExplodeEvent
typealias FoodLevelChange = FoodLevelChangeEvent
typealias HorseJumpEvent = HorseJumpEvent
typealias ItemDespawn = ItemDespawnEvent
typealias ItemMerge = ItemMergeEvent
typealias ItemSpawn = ItemSpawnEvent
typealias LingeringPotionSplash = LingeringPotionSplashEvent
typealias PiglinBarter = PiglinBarterEvent
typealias PigZap = PigZapEvent
typealias PigZombieAnger = PigZombieAngerEvent
typealias PlayerLeashEntity = PlayerLeashEntityEvent
typealias PotionSplash = PotionSplashEvent
typealias ProjectileHit = ProjectileHitEvent
typealias ProjectileLaunch = ProjectileLaunchEvent
typealias SheepDyeWool = SheepDyeWoolEvent
typealias SheepRegrowWool = SheepRegrowWoolEvent
typealias SlimeSplit = SlimeSplitEvent
typealias SpawnerSpawn = SpawnerSpawnEvent
typealias StriderTemperatureChange = StriderTemperatureChangeEvent
typealias VillagerAcquireTrade = VillagerAcquireTradeEvent
typealias VillagerCareerChange = VillagerCareerChangeEvent
typealias VillagerReplenishTrade = VillagerReplenishTradeEvent

typealias Action = org.bukkit.event.block.Action
typealias BlockBreak = BlockBreakEvent
typealias BlockBurn = BlockBurnEvent
typealias BlockCanBuilder = BlockCanBuildEvent
typealias BlockCook = BlockCookEvent
typealias BlockDamageAbort = BlockDamageAbortEvent
typealias BlockDamage = BlockDamageEvent
typealias BlockDispenseArmor = BlockDispenseArmorEvent
typealias BlockDispense = BlockDispenseEvent
typealias BlockDropItem = BlockDropItemEvent
typealias BlockExp = BlockExpEvent
typealias BlockExplode = BlockExplodeEvent
typealias BlockFade = BlockFadeEvent
typealias BlockFertilize = BlockFertilizeEvent
typealias BlockForm = BlockFormEvent
typealias BlockFromTo = BlockFromToEvent
typealias BlockGrow = BlockGrowEvent
typealias BlockIgnite = BlockIgniteEvent
typealias BlockMultiPlace = BlockMultiPlaceEvent
typealias BlockPiston = BlockPistonEvent
typealias BlockPistonExtend = BlockPistonExtendEvent
typealias BlockPistonRetract = BlockPistonRetractEvent
typealias BlockPlace = BlockPlaceEvent
typealias BlockReceiveGame = BlockReceiveGameEvent
typealias BlockRedstone = BlockRedstoneEvent
typealias BlockShearEntity = BlockShearEntityEvent
typealias BlockSpread = BlockSpreadEvent
typealias CauldronLevelChange = CauldronLevelChangeEvent
typealias EntityBlockForm = EntityBlockFormEvent
typealias FluidLevelChange = FluidLevelChangeEvent
typealias LeavesDecay = LeavesDecayEvent
typealias MoistureChange = MoistureChangeEvent
typealias NotePlay = NotePlayEvent
typealias SignChange = SignChangeEvent
typealias SpongeAbsorb = SpongeAbsorbEvent

typealias EnchantItem = EnchantItemEvent
typealias PrepareItemEnchant = PrepareItemEnchantEvent

typealias HangingBreakByEntity = HangingBreakByEntityEvent
typealias Hanging = HangingEvent
typealias HangingBreak = HangingBreakEvent
typealias HangingPlace = HangingBreakEvent

typealias Brew = BrewEvent
typealias BrewingStandFuel = BrewingStandFuelEvent
typealias ClickType = org.bukkit.event.inventory.ClickType
typealias CraftItem = CraftItemEvent
typealias FurnaceBurn = FurnaceBurnEvent
typealias FurnaceExtract = FurnaceExtractEvent
typealias FurnaceSmelt = FurnaceSmeltEvent
typealias FurnaceStartSmelt = FurnaceStartSmeltEvent
typealias InventoryAction = org.bukkit.event.inventory.InventoryAction
typealias InventoryClick = InventoryClickEvent
typealias InventoryClose = InventoryCloseEvent
typealias InventoryCreative = InventoryCreativeEvent
typealias InventoryDrag = InventoryDragEvent
typealias InventoryInteract = InventoryInteractEvent
typealias InventoryMoveItem = InventoryMoveItemEvent
typealias InventoryOpen = InventoryOpenEvent
typealias InventoryPickupItem = InventoryPickupItemEvent
typealias InventoryType = org.bukkit.event.inventory.InventoryType
typealias PrepareAnvil = PrepareAnvilEvent
typealias PrepareItemCraft = PrepareItemCraftEvent
typealias PrepareSmithing = PrepareSmithingEvent
typealias SmithItem = SmithItemEvent
typealias TradeSelect = TradeSelectEvent
typealias AsyncPlayerChat = AsyncPlayerChatEvent
typealias AsyncPlayerPreLogin = AsyncPlayerPreLoginEvent
typealias PlayerAdvancementDone = PlayerAdvancementDoneEvent
typealias PlayerAnimation = PlayerAnimationEvent
typealias PlayerArmorStandManipulate = PlayerArmorStandManipulateEvent
typealias PlayerTakeLecternBook = PlayerTakeLecternBookEvent
typealias PlayerHarvestBlock = PlayerHarvestBlockEvent
typealias PlayerBucketEmpty = PlayerBucketEmptyEvent
typealias PlayerRiptide = PlayerRiptideEvent
typealias PlayerDropItem = PlayerDropItemEvent
typealias PlayerVelocity = PlayerVelocityEvent
typealias PlayerSwapHandItems = PlayerSwapHandItemsEvent
typealias PlayerInteractEntity = PlayerInteractEntityEvent
typealias PlayerMove = PlayerMoveEvent
typealias PlayerCommandPreprocess = PlayerCommandPreprocessEvent
typealias PlayerPickupArrow = PlayerPickupArrowEvent
typealias PlayerLogin = PlayerLoginEvent
typealias PlayerInteract = PlayerInteractEvent
typealias PlayerBucketEntity = PlayerBucketEntityEvent
typealias PlayerShearEntity = PlayerShearEntityEvent
typealias PlayerResourcePackStatus = PlayerResourcePackStatusEvent
typealias PlayerChangedMainHand = PlayerChangedMainHandEvent
typealias PlayerEditBook = PlayerEditBookEvent
typealias PlayerPortal = PlayerPortalEvent
typealias PlayerShowEntity = PlayerShowEntityEvent
typealias PlayerUnleashEntity = PlayerUnleashEntityEvent
typealias PlayerKick = PlayerKickEvent
typealias PlayerLocaleChange = PlayerLocaleChangeEvent
typealias PlayerBedLeave = PlayerBedLeaveEvent
typealias PlayerToggleFlight = PlayerToggleFlightEvent
typealias PlayerBucketFill = PlayerBucketFillEvent
typealias PlayerCommandSend = PlayerCommandSendEvent
typealias PlayerStatisticIncrement = PlayerStatisticIncrementEvent
typealias PlayerRespawn = PlayerRespawnEvent
typealias PlayerToggleSneak = PlayerToggleSneakEvent
typealias PlayerUnregisterChannel = PlayerUnregisterChannelEvent
typealias PlayerBucketFish = PlayerBucketFishEvent
typealias PlayerChannel = PlayerChannelEvent
typealias PlayerRegisterChannel = PlayerRegisterChannelEvent
typealias PlayerHideEntity = PlayerHideEntityEvent
typealias PlayerEggThrow = PlayerEggThrowEvent
typealias PlayerTeleport = PlayerTeleportEvent
typealias PlayerPreLogin = PlayerPreLoginEvent
typealias PlayerInteractAtEntity = PlayerInteractAtEntityEvent
typealias PlayerItemConsume = PlayerItemConsumeEvent
typealias PlayerBucket = PlayerBucketEvent
typealias PlayerAnimationType = org.bukkit.event.player.PlayerAnimationType

typealias Raid = RaidEvent
typealias RaidFinish = RaidFinishEvent
typealias RaidSpawnWave = RaidSpawnWaveEvent
typealias RaidStop = RaidStopEvent
typealias RaidTrigger = RaidTriggerEvent

typealias BroadcastMessage = BroadcastMessageEvent
typealias MapInitialize = MapInitializeEvent
typealias PluginDisable = PluginDisableEvent
typealias PluginEnable = PluginEnableEvent
typealias Plugin = PluginEvent
typealias RemoteServerCommand = RemoteServerCommandEvent
typealias ServerLoad = ServerLoadEvent
typealias ServerCommand = ServerCommandEvent
typealias ServerListPing = ServerListPingEvent
typealias Service = ServiceEvent
typealias ServiceRegister = ServiceRegisterEvent
typealias Server = ServerEvent
typealias TabComplete = TabCompleteEvent
typealias ServiceUnregister = ServiceUnregisterEvent

typealias VehicleBlockCollision = VehicleBlockCollisionEvent
typealias VehicleCollision = VehicleCollisionEvent
typealias VehicleCreate = VehicleCreateEvent
typealias VehicleDamage = VehicleDamageEvent
typealias VehicleDestroy = VehicleDestroyEvent
typealias VehicleEnter = VehicleEnterEvent
typealias VehicleEntityCollision = VehicleEntityCollisionEvent
typealias Vehicle = VehicleEvent
typealias VehicleExit = VehicleExitEvent
typealias VehicleMove = VehicleMoveEvent
typealias VehicleUpdate = VehicleUpdateEvent

typealias LightningStrike = LightningStrikeEvent
typealias ThunderChange = ThunderChangeEvent
typealias WeatherChange = WeatherChangeEvent
typealias Weather = WeatherEvent

typealias LootGenerate = LootGenerateEvent
typealias ChunkUnload = ChunkUnloadEvent
typealias GenericGame = GenericGameEvent
typealias EntitiesUnload = EntitiesUnloadEvent
typealias ChunkPopulate = ChunkPopulateEvent
typealias EntitiesLoad = EntitiesLoadEvent
typealias ChunkLoad = ChunkLoadEvent
typealias PortalCreate = PortalCreateEvent
typealias Chunk = ChunkEvent
typealias WorldUnload = WorldUnloadEvent
typealias SpawnChange = SpawnChangeEvent
typealias StructureGrow = StructureGrowEvent
typealias TimeSkip = TimeSkipEvent
typealias WorldInit = WorldInitEvent
typealias WorldSave = WorldSaveEvent
typealias World = WorldEvent
typealias WorldLoad = WorldLoadEvent