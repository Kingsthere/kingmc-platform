@file:Suppress("unused", "DEPRECATION")

package kingmc.platform.bukkit.event

import org.bukkit.event.block.*
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.entity.*
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.hanging.HangingEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.*
import org.bukkit.event.raid.*
import org.bukkit.event.server.*
import org.bukkit.event.vehicle.*
import org.bukkit.event.weather.LightningStrikeEvent
import org.bukkit.event.weather.ThunderChangeEvent
import org.bukkit.event.weather.WeatherChangeEvent
import org.bukkit.event.weather.WeatherEvent
import org.bukkit.event.world.*

typealias BukkitPlayerJoinEvent = PlayerJoinEvent
typealias BukkitPlayerQuitEvent = PlayerQuitEvent

typealias BukkitPlayerDeathEvent = PlayerDeathEvent
typealias BukkitAreaEffectCloudApplyEvent = AreaEffectCloudApplyEvent
typealias BukkitArrowBodyCountChangeEvent = ArrowBodyCountChangeEvent
typealias BukkitBatToggleSleepEvent = BatToggleSleepEvent
typealias BukkitCreatureSpawnEvent = CreatureSpawnEvent
typealias BukkitCreeperPowerEvent = CreeperPowerEvent
typealias BukkitEnderDragonChangePhaseEvent = EnderDragonChangePhaseEvent
typealias BukkitEntityAirChangeEvent = EntityAirChangeEvent
typealias BukkitEntityBreakDoorEvent = EntityBreakDoorEvent
typealias BukkitEntityBreedEvent = EntityBreedEvent
typealias BukkitEntityChangeBlockEvent = EntityChangeBlockEvent
typealias BukkitEntityCombustByBlockEvent = EntityCombustByBlockEvent
typealias BukkitEntityCombustByEntityEvent = EntityCombustByEntityEvent
typealias BukkitEntityCombustEvent = EntityCombustEvent
typealias BukkitEntityCreatePortalEvent = EntityCreatePortalEvent
typealias BukkitEntityDamageByBlockEvent = EntityDamageByBlockEvent
typealias BukkitEntityDamageByEntityEvent = EntityDamageByEntityEvent
typealias BukkitEntityDamageEvent = EntityDamageEvent
typealias BukkitEntityDropItemEvent = EntityDropItemEvent
typealias BukkitEntityEnterBlockEvent = EntityEnterBlockEvent
typealias BukkitEntityEnterLoveModeEvent = EntityEnterLoveModeEvent
typealias BukkitEntityExhaustionEvent = EntityExhaustionEvent
typealias BukkitEntityExplodeEvent = EntityExplodeEvent
typealias BukkitEntityInteractEvent = EntityInteractEvent
typealias BukkitEntityPickupItemEvent = EntityPickupItemEvent
typealias BukkitEntityPlaceEvent = EntityPlaceEvent
typealias BukkitEntityPortalEnterEvent = EntityPortalEnterEvent
typealias BukkitEntityPortalEventEvent = EntityPortalEvent
typealias BukkitEntityPortalEvent = EntityPortalExitEvent
typealias BukkitEntityPortalExitEvent = EntityPortalExitEvent
typealias BukkitEntityPoseChangeEvent = EntityPoseChangeEvent
typealias BukkitEntityPotionEffectEvent = EntityPotionEffectEvent
typealias BukkitEntityRegainHealthEvent = EntityRegainHealthEvent
typealias BukkitEntityResurrectEvent = EntityResurrectEvent
typealias BukkitEntityShootBowEvent = EntityShootBowEvent
typealias BukkitEntitySpawnEvent = EntitySpawnEvent
typealias BukkitEntitySpellCastEvent = EntitySpellCastEvent
typealias BukkitEntityTameEvent = EntityTameEvent
typealias BukkitEntityTargetEvent = EntityTargetEvent
typealias BukkitEntityTargetLivingEntityEvent = EntityTargetLivingEntityEvent
typealias BukkitEntityTeleportEvent = EntityTeleportEvent
typealias BukkitEntityToggleGlideEvent = EntityToggleGlideEvent
typealias BukkitEntityToggleSwimEvent = EntityToggleSwimEvent
typealias BukkitEntityTransformEvent = EntityTransformEvent
typealias BukkitEntityUnleashEvent = EntityUnleashEvent
typealias BukkitExpBottleEvent = ExpBottleEvent
typealias BukkitExplosionPrimeEvent = ExplosionPrimeEvent
typealias BukkitFireworkExplodeEvent = FireworkExplodeEvent
typealias BukkitFoodLevelChangeEvent = FoodLevelChangeEvent
typealias BukkitHorseJumpEventEvent = HorseJumpEvent
typealias BukkitItemDespawnEvent = ItemDespawnEvent
typealias BukkitItemMergeEvent = ItemMergeEvent
typealias BukkitItemSpawnEvent = ItemSpawnEvent
typealias BukkitLingeringPotionSplashEvent = LingeringPotionSplashEvent
typealias BukkitPiglinBarterEvent = PiglinBarterEvent
typealias BukkitPigZapEvent = PigZapEvent
typealias BukkitPigZombieAngerEvent = PigZombieAngerEvent
typealias BukkitPlayerLeashEntityEvent = PlayerLeashEntityEvent
typealias BukkitPotionSplashEvent = PotionSplashEvent
typealias BukkitProjectileHitEvent = ProjectileHitEvent
typealias BukkitProjectileLaunchEvent = ProjectileLaunchEvent
typealias BukkitSheepDyeWoolEvent = SheepDyeWoolEvent
typealias BukkitSheepRegrowWoolEvent = SheepRegrowWoolEvent
typealias BukkitSlimeSplitEvent = SlimeSplitEvent
typealias BukkitSpawnerSpawnEvent = SpawnerSpawnEvent
typealias BukkitStriderTemperatureChangeEvent = StriderTemperatureChangeEvent
typealias BukkitVillagerAcquireTradeEvent = VillagerAcquireTradeEvent
typealias BukkitVillagerCareerChangeEvent = VillagerCareerChangeEvent
typealias BukkitVillagerReplenishTradeEvent = VillagerReplenishTradeEvent

typealias BukkitActionEvent = org.bukkit.event.block.Action
typealias BukkitBlockBreakEvent = BlockBreakEvent
typealias BukkitBlockBurnEvent = BlockBurnEvent
typealias BukkitBlockCanBuilderEvent = BlockCanBuildEvent
typealias BukkitBlockCookEvent = BlockCookEvent
typealias BukkitBlockDamageAbortEvent = BlockDamageAbortEvent
typealias BukkitBlockDamageEvent = BlockDamageEvent
typealias BukkitBlockDispenseArmorEvent = BlockDispenseArmorEvent
typealias BukkitBlockDispenseEvent = BlockDispenseEvent
typealias BukkitBlockDropItemEvent = BlockDropItemEvent
typealias BukkitBlockExpEvent = BlockExpEvent
typealias BukkitBlockExplodeEvent = BlockExplodeEvent
typealias BukkitBlockFadeEvent = BlockFadeEvent
typealias BukkitBlockFertilizeEvent = BlockFertilizeEvent
typealias BukkitBlockFormEvent = BlockFormEvent
typealias BukkitBlockFromToEvent = BlockFromToEvent
typealias BukkitBlockGrowEvent = BlockGrowEvent
typealias BukkitBlockIgniteEvent = BlockIgniteEvent
typealias BukkitBlockMultiPlaceEvent = BlockMultiPlaceEvent
typealias BukkitBlockPistonEvent = BlockPistonEvent
typealias BukkitBlockPistonExtendEvent = BlockPistonExtendEvent
typealias BukkitBlockPistonRetractEvent = BlockPistonRetractEvent
typealias BukkitBlockPlaceEvent = BlockPlaceEvent
typealias BukkitBlockReceiveGameEvent = BlockReceiveGameEvent
typealias BukkitBlockRedstoneEvent = BlockRedstoneEvent
typealias BukkitBlockShearEntityEvent = BlockShearEntityEvent
typealias BukkitBlockSpreadEvent = BlockSpreadEvent
typealias BukkitCauldronLevelChangeEvent = CauldronLevelChangeEvent
typealias BukkitEntityBlockFormEvent = EntityBlockFormEvent
typealias BukkitFluidLevelChangeEvent = FluidLevelChangeEvent
typealias BukkitLeavesDecayEvent = LeavesDecayEvent
typealias BukkitMoistureChangeEvent = MoistureChangeEvent
typealias BukkitNotePlayEvent = NotePlayEvent
typealias BukkitSignChangeEvent = SignChangeEvent
typealias BukkitSpongeAbsorbEvent = SpongeAbsorbEvent

typealias BukkitEnchantItemEvent = EnchantItemEvent
typealias BukkitPrepareItemEnchantEvent = PrepareItemEnchantEvent

typealias BukkitHangingBreakByEntityEvent = HangingBreakByEntityEvent
typealias BukkitHangingEvent = HangingEvent
typealias BukkitHangingBreakEvent = HangingBreakEvent
typealias BukkitHangingPlaceEvent = HangingBreakEvent

typealias BukkitBrewEvent = BrewEvent
typealias BukkitBrewingStandFuelEvent = BrewingStandFuelEvent
typealias BukkitClickTypeEvent = org.bukkit.event.inventory.ClickType
typealias BukkitCraftItemEvent = CraftItemEvent
typealias BukkitFurnaceBurnEvent = FurnaceBurnEvent
typealias BukkitFurnaceExtractEvent = FurnaceExtractEvent
typealias BukkitFurnaceSmeltEvent = FurnaceSmeltEvent
typealias BukkitFurnaceStartSmeltEvent = FurnaceStartSmeltEvent
typealias BukkitInventoryActionEvent = org.bukkit.event.inventory.InventoryAction
typealias BukkitInventoryClickEvent = InventoryClickEvent
typealias BukkitInventoryCloseEvent = InventoryCloseEvent
typealias BukkitInventoryCreativeEvent = InventoryCreativeEvent
typealias BukkitInventoryDragEvent = InventoryDragEvent
typealias BukkitInventoryInteractEvent = InventoryInteractEvent
typealias BukkitInventoryMoveItemEvent = InventoryMoveItemEvent
typealias BukkitInventoryOpenEvent = InventoryOpenEvent
typealias BukkitInventoryPickupItemEvent = InventoryPickupItemEvent
typealias BukkitInventoryTypeEvent = org.bukkit.event.inventory.InventoryType
typealias BukkitPrepareAnvilEvent = PrepareAnvilEvent
typealias BukkitPrepareItemCraftEvent = PrepareItemCraftEvent
typealias BukkitPrepareSmithingEvent = PrepareSmithingEvent
typealias BukkitSmithItemEvent = SmithItemEvent
typealias BukkitTradeSelectEvent = TradeSelectEvent
typealias BukkitAsyncPlayerChatEvent = AsyncPlayerChatEvent
typealias BukkitAsyncPlayerPreLoginEvent = AsyncPlayerPreLoginEvent
typealias BukkitPlayerAdvancementDoneEvent = PlayerAdvancementDoneEvent
typealias BukkitPlayerAnimationEvent = PlayerAnimationEvent
typealias BukkitPlayerArmorStandManipulateEvent = PlayerArmorStandManipulateEvent
typealias BukkitPlayerTakeLecternBookEvent = PlayerTakeLecternBookEvent
typealias BukkitPlayerHarvestBlockEvent = PlayerHarvestBlockEvent
typealias BukkitPlayerBucketEmptyEvent = PlayerBucketEmptyEvent
typealias BukkitPlayerRiptideEvent = PlayerRiptideEvent
typealias BukkitPlayerDropItemEvent = PlayerDropItemEvent
typealias BukkitPlayerVelocityEvent = PlayerVelocityEvent
typealias BukkitPlayerSwapHandItemsEvent = PlayerSwapHandItemsEvent
typealias BukkitPlayerInteractEntityEvent = PlayerInteractEntityEvent
typealias BukkitPlayerMoveEvent = PlayerMoveEvent
typealias BukkitPlayerCommandPreprocessEvent = PlayerCommandPreprocessEvent
typealias BukkitPlayerPickupArrowEvent = PlayerPickupArrowEvent
typealias BukkitPlayerLoginEvent = PlayerLoginEvent
typealias BukkitPlayerInteractEvent = PlayerInteractEvent
typealias BukkitPlayerBucketEntityEvent = PlayerBucketEntityEvent
typealias BukkitPlayerShearEntityEvent = PlayerShearEntityEvent
typealias BukkitPlayerResourcePackStatusEvent = PlayerResourcePackStatusEvent
typealias BukkitPlayerChangedMainHandEvent = PlayerChangedMainHandEvent
typealias BukkitPlayerEditBookEvent = PlayerEditBookEvent
typealias BukkitPlayerPortalEvent = PlayerPortalEvent
typealias BukkitPlayerShowEntityEvent = PlayerShowEntityEvent
typealias BukkitPlayerUnleashEntityEvent = PlayerUnleashEntityEvent
typealias BukkitPlayerKickEvent = PlayerKickEvent
typealias BukkitPlayerLocaleChangeEvent = PlayerLocaleChangeEvent
typealias BukkitPlayerBedLeaveEvent = PlayerBedLeaveEvent
typealias BukkitPlayerToggleFlightEvent = PlayerToggleFlightEvent
typealias BukkitPlayerBucketFillEvent = PlayerBucketFillEvent
typealias BukkitPlayerCommandSendEvent = PlayerCommandSendEvent
typealias BukkitPlayerStatisticIncrementEvent = PlayerStatisticIncrementEvent
typealias BukkitPlayerRespawnEvent = PlayerRespawnEvent
typealias BukkitPlayerToggleSneakEvent = PlayerToggleSneakEvent
typealias BukkitPlayerUnregisterChannelEvent = PlayerUnregisterChannelEvent
typealias BukkitPlayerBucketFishEvent = PlayerBucketFishEvent
typealias BukkitPlayerChannelEvent = PlayerChannelEvent
typealias BukkitPlayerRegisterChannelEvent = PlayerRegisterChannelEvent
typealias BukkitPlayerHideEntityEvent = PlayerHideEntityEvent
typealias BukkitPlayerEggThrowEvent = PlayerEggThrowEvent
typealias BukkitPlayerTeleportEvent = PlayerTeleportEvent
typealias BukkitPlayerPreLoginEvent = PlayerPreLoginEvent
typealias BukkitPlayerInteractAtEntityEvent = PlayerInteractAtEntityEvent
typealias BukkitPlayerItemConsumeEvent = PlayerItemConsumeEvent
typealias BukkitPlayerBucketEvent = PlayerBucketEvent
typealias BukkitPlayerAnimationTypeEvent = org.bukkit.event.player.PlayerAnimationType

typealias BukkitRaidEvent = RaidEvent
typealias BukkitRaidFinishEvent = RaidFinishEvent
typealias BukkitRaidSpawnWaveEvent = RaidSpawnWaveEvent
typealias BukkitRaidStopEvent = RaidStopEvent
typealias BukkitRaidTriggerEvent = RaidTriggerEvent

typealias BukkitBroadcastMessageEvent = BroadcastMessageEvent
typealias BukkitMapInitializeEvent = MapInitializeEvent
typealias BukkitPluginDisableEvent = PluginDisableEvent
typealias BukkitPluginEnableEvent = PluginEnableEvent
typealias BukkitPluginEvent = PluginEvent
typealias BukkitRemoteServerCommandEvent = RemoteServerCommandEvent
typealias BukkitServerLoadEvent = ServerLoadEvent
typealias BukkitServerCommandEvent = ServerCommandEvent
typealias BukkitServerListPingEvent = ServerListPingEvent
typealias BukkitServiceEvent = ServiceEvent
typealias BukkitServiceRegisterEvent = ServiceRegisterEvent
typealias BukkitServerEvent = ServerEvent
typealias BukkitTabCompleteEvent = TabCompleteEvent
typealias BukkitServiceUnregisterEvent = ServiceUnregisterEvent

typealias BukkitVehicleBlockCollisionEvent = VehicleBlockCollisionEvent
typealias BukkitVehicleCollisionEvent = VehicleCollisionEvent
typealias BukkitVehicleCreateEvent = VehicleCreateEvent
typealias BukkitVehicleDamageEvent = VehicleDamageEvent
typealias BukkitVehicleDestroyEvent = VehicleDestroyEvent
typealias BukkitVehicleEnterEvent = VehicleEnterEvent
typealias BukkitVehicleEntityCollisionEvent = VehicleEntityCollisionEvent
typealias BukkitVehicleEvent = VehicleEvent
typealias BukkitVehicleExitEvent = VehicleExitEvent
typealias BukkitVehicleMoveEvent = VehicleMoveEvent
typealias BukkitVehicleUpdateEvent = VehicleUpdateEvent

typealias BukkitLightningStrikeEvent = LightningStrikeEvent
typealias BukkitThunderChangeEvent = ThunderChangeEvent
typealias BukkitWeatherChangeEvent = WeatherChangeEvent
typealias BukkitWeatherEvent = WeatherEvent

typealias BukkitLootGenerateEvent = LootGenerateEvent
typealias BukkitChunkUnloadEvent = ChunkUnloadEvent
typealias BukkitGenericGameEvent = GenericGameEvent
typealias BukkitEntitiesUnloadEvent = EntitiesUnloadEvent
typealias BukkitChunkPopulateEvent = ChunkPopulateEvent
typealias BukkitEntitiesLoadEvent = EntitiesLoadEvent
typealias BukkitChunkLoadEvent = ChunkLoadEvent
typealias BukkitPortalCreateEvent = PortalCreateEvent
typealias BukkitChunkEvent = ChunkEvent
typealias BukkitWorldUnloadEvent = WorldUnloadEvent
typealias BukkitSpawnChangeEvent = SpawnChangeEvent
typealias BukkitStructureGrowEvent = StructureGrowEvent
typealias BukkitTimeSkipEvent = TimeSkipEvent
typealias BukkitWorldInitEvent = WorldInitEvent
typealias BukkitWorldSaveEvent = WorldSaveEvent
typealias BukkitWorldEvent = WorldEvent
typealias BukkitWorldLoadEvent = WorldLoadEvent