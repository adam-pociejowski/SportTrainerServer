package com.valverde.sporttrainerserver.zwift.proto;

public final class ZwiftMessages {
  private ZwiftMessages() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PlayerStateOrBuilder extends
      // @@protoc_insertion_point(interface_extends:zwift.PlayerState)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 id = 1;</code>
     */
    int getId();

    /**
     * <code>int64 worldTime = 2;</code>
     */
    long getWorldTime();

    /**
     * <code>int32 distance = 3;</code>
     */
    int getDistance();

    /**
     * <code>int32 roadTime = 4;</code>
     */
    int getRoadTime();

    /**
     * <code>int32 laps = 5;</code>
     */
    int getLaps();

    /**
     * <code>int32 speed = 6;</code>
     */
    int getSpeed();

    /**
     * <code>int32 roadPosition = 8;</code>
     */
    int getRoadPosition();

    /**
     * <code>int32 cadenceUHz = 9;</code>
     */
    int getCadenceUHz();

    /**
     * <code>int32 heartrate = 11;</code>
     */
    int getHeartrate();

    /**
     * <code>int32 power = 12;</code>
     */
    int getPower();

    /**
     * <code>int64 heading = 13;</code>
     */
    long getHeading();

    /**
     * <code>int32 lean = 14;</code>
     */
    int getLean();

    /**
     * <code>int32 climbing = 15;</code>
     */
    int getClimbing();

    /**
     * <code>int32 time = 16;</code>
     */
    int getTime();

    /**
     * <code>int32 f19 = 19;</code>
     */
    int getF19();

    /**
     * <code>int32 f20 = 20;</code>
     */
    int getF20();

    /**
     * <code>int32 progress = 21;</code>
     */
    int getProgress();

    /**
     * <code>int64 customisationId = 22;</code>
     */
    long getCustomisationId();

    /**
     * <code>int32 justWatching = 23;</code>
     */
    int getJustWatching();

    /**
     * <code>int32 calories = 24;</code>
     */
    int getCalories();

    /**
     * <code>float x = 25;</code>
     */
    float getX();

    /**
     * <code>float altitude = 26;</code>
     */
    float getAltitude();

    /**
     * <code>float y = 27;</code>
     */
    float getY();

    /**
     * <code>int32 watchingRiderId = 28;</code>
     */
    int getWatchingRiderId();

    /**
     * <code>int32 groupId = 29;</code>
     */
    int getGroupId();

    /**
     * <code>int64 sport = 31;</code>
     */
    long getSport();
  }
  /**
   * Protobuf type {@code zwift.PlayerState}
   */
  public  static final class PlayerState extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:zwift.PlayerState)
      PlayerStateOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use PlayerState.newBuilder() to construct.
    private PlayerState(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PlayerState() {
      id_ = 0;
      worldTime_ = 0L;
      distance_ = 0;
      roadTime_ = 0;
      laps_ = 0;
      speed_ = 0;
      roadPosition_ = 0;
      cadenceUHz_ = 0;
      heartrate_ = 0;
      power_ = 0;
      heading_ = 0L;
      lean_ = 0;
      climbing_ = 0;
      time_ = 0;
      f19_ = 0;
      f20_ = 0;
      progress_ = 0;
      customisationId_ = 0L;
      justWatching_ = 0;
      calories_ = 0;
      x_ = 0F;
      altitude_ = 0F;
      y_ = 0F;
      watchingRiderId_ = 0;
      groupId_ = 0;
      sport_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerState(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              id_ = input.readInt32();
              break;
            }
            case 16: {

              worldTime_ = input.readInt64();
              break;
            }
            case 24: {

              distance_ = input.readInt32();
              break;
            }
            case 32: {

              roadTime_ = input.readInt32();
              break;
            }
            case 40: {

              laps_ = input.readInt32();
              break;
            }
            case 48: {

              speed_ = input.readInt32();
              break;
            }
            case 64: {

              roadPosition_ = input.readInt32();
              break;
            }
            case 72: {

              cadenceUHz_ = input.readInt32();
              break;
            }
            case 88: {

              heartrate_ = input.readInt32();
              break;
            }
            case 96: {

              power_ = input.readInt32();
              break;
            }
            case 104: {

              heading_ = input.readInt64();
              break;
            }
            case 112: {

              lean_ = input.readInt32();
              break;
            }
            case 120: {

              climbing_ = input.readInt32();
              break;
            }
            case 128: {

              time_ = input.readInt32();
              break;
            }
            case 152: {

              f19_ = input.readInt32();
              break;
            }
            case 160: {

              f20_ = input.readInt32();
              break;
            }
            case 168: {

              progress_ = input.readInt32();
              break;
            }
            case 176: {

              customisationId_ = input.readInt64();
              break;
            }
            case 184: {

              justWatching_ = input.readInt32();
              break;
            }
            case 192: {

              calories_ = input.readInt32();
              break;
            }
            case 205: {

              x_ = input.readFloat();
              break;
            }
            case 213: {

              altitude_ = input.readFloat();
              break;
            }
            case 221: {

              y_ = input.readFloat();
              break;
            }
            case 224: {

              watchingRiderId_ = input.readInt32();
              break;
            }
            case 232: {

              groupId_ = input.readInt32();
              break;
            }
            case 248: {

              sport_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return internal_static_zwift_PlayerState_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return internal_static_zwift_PlayerState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState.class, com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int WORLDTIME_FIELD_NUMBER = 2;
    private long worldTime_;
    /**
     * <code>int64 worldTime = 2;</code>
     */
    public long getWorldTime() {
      return worldTime_;
    }

    public static final int DISTANCE_FIELD_NUMBER = 3;
    private int distance_;
    /**
     * <code>int32 distance = 3;</code>
     */
    public int getDistance() {
      return distance_;
    }

    public static final int ROADTIME_FIELD_NUMBER = 4;
    private int roadTime_;
    /**
     * <code>int32 roadTime = 4;</code>
     */
    public int getRoadTime() {
      return roadTime_;
    }

    public static final int LAPS_FIELD_NUMBER = 5;
    private int laps_;
    /**
     * <code>int32 laps = 5;</code>
     */
    public int getLaps() {
      return laps_;
    }

    public static final int SPEED_FIELD_NUMBER = 6;
    private int speed_;
    /**
     * <code>int32 speed = 6;</code>
     */
    public int getSpeed() {
      return speed_;
    }

    public static final int ROADPOSITION_FIELD_NUMBER = 8;
    private int roadPosition_;
    /**
     * <code>int32 roadPosition = 8;</code>
     */
    public int getRoadPosition() {
      return roadPosition_;
    }

    public static final int CADENCEUHZ_FIELD_NUMBER = 9;
    private int cadenceUHz_;
    /**
     * <code>int32 cadenceUHz = 9;</code>
     */
    public int getCadenceUHz() {
      return cadenceUHz_;
    }

    public static final int HEARTRATE_FIELD_NUMBER = 11;
    private int heartrate_;
    /**
     * <code>int32 heartrate = 11;</code>
     */
    public int getHeartrate() {
      return heartrate_;
    }

    public static final int POWER_FIELD_NUMBER = 12;
    private int power_;
    /**
     * <code>int32 power = 12;</code>
     */
    public int getPower() {
      return power_;
    }

    public static final int HEADING_FIELD_NUMBER = 13;
    private long heading_;
    /**
     * <code>int64 heading = 13;</code>
     */
    public long getHeading() {
      return heading_;
    }

    public static final int LEAN_FIELD_NUMBER = 14;
    private int lean_;
    /**
     * <code>int32 lean = 14;</code>
     */
    public int getLean() {
      return lean_;
    }

    public static final int CLIMBING_FIELD_NUMBER = 15;
    private int climbing_;
    /**
     * <code>int32 climbing = 15;</code>
     */
    public int getClimbing() {
      return climbing_;
    }

    public static final int TIME_FIELD_NUMBER = 16;
    private int time_;
    /**
     * <code>int32 time = 16;</code>
     */
    public int getTime() {
      return time_;
    }

    public static final int F19_FIELD_NUMBER = 19;
    private int f19_;
    /**
     * <code>int32 f19 = 19;</code>
     */
    public int getF19() {
      return f19_;
    }

    public static final int F20_FIELD_NUMBER = 20;
    private int f20_;
    /**
     * <code>int32 f20 = 20;</code>
     */
    public int getF20() {
      return f20_;
    }

    public static final int PROGRESS_FIELD_NUMBER = 21;
    private int progress_;
    /**
     * <code>int32 progress = 21;</code>
     */
    public int getProgress() {
      return progress_;
    }

    public static final int CUSTOMISATIONID_FIELD_NUMBER = 22;
    private long customisationId_;
    /**
     * <code>int64 customisationId = 22;</code>
     */
    public long getCustomisationId() {
      return customisationId_;
    }

    public static final int JUSTWATCHING_FIELD_NUMBER = 23;
    private int justWatching_;
    /**
     * <code>int32 justWatching = 23;</code>
     */
    public int getJustWatching() {
      return justWatching_;
    }

    public static final int CALORIES_FIELD_NUMBER = 24;
    private int calories_;
    /**
     * <code>int32 calories = 24;</code>
     */
    public int getCalories() {
      return calories_;
    }

    public static final int X_FIELD_NUMBER = 25;
    private float x_;
    /**
     * <code>float x = 25;</code>
     */
    public float getX() {
      return x_;
    }

    public static final int ALTITUDE_FIELD_NUMBER = 26;
    private float altitude_;
    /**
     * <code>float altitude = 26;</code>
     */
    public float getAltitude() {
      return altitude_;
    }

    public static final int Y_FIELD_NUMBER = 27;
    private float y_;
    /**
     * <code>float y = 27;</code>
     */
    public float getY() {
      return y_;
    }

    public static final int WATCHINGRIDERID_FIELD_NUMBER = 28;
    private int watchingRiderId_;
    /**
     * <code>int32 watchingRiderId = 28;</code>
     */
    public int getWatchingRiderId() {
      return watchingRiderId_;
    }

    public static final int GROUPID_FIELD_NUMBER = 29;
    private int groupId_;
    /**
     * <code>int32 groupId = 29;</code>
     */
    public int getGroupId() {
      return groupId_;
    }

    public static final int SPORT_FIELD_NUMBER = 31;
    private long sport_;
    /**
     * <code>int64 sport = 31;</code>
     */
    public long getSport() {
      return sport_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (id_ != 0) {
        output.writeInt32(1, id_);
      }
      if (worldTime_ != 0L) {
        output.writeInt64(2, worldTime_);
      }
      if (distance_ != 0) {
        output.writeInt32(3, distance_);
      }
      if (roadTime_ != 0) {
        output.writeInt32(4, roadTime_);
      }
      if (laps_ != 0) {
        output.writeInt32(5, laps_);
      }
      if (speed_ != 0) {
        output.writeInt32(6, speed_);
      }
      if (roadPosition_ != 0) {
        output.writeInt32(8, roadPosition_);
      }
      if (cadenceUHz_ != 0) {
        output.writeInt32(9, cadenceUHz_);
      }
      if (heartrate_ != 0) {
        output.writeInt32(11, heartrate_);
      }
      if (power_ != 0) {
        output.writeInt32(12, power_);
      }
      if (heading_ != 0L) {
        output.writeInt64(13, heading_);
      }
      if (lean_ != 0) {
        output.writeInt32(14, lean_);
      }
      if (climbing_ != 0) {
        output.writeInt32(15, climbing_);
      }
      if (time_ != 0) {
        output.writeInt32(16, time_);
      }
      if (f19_ != 0) {
        output.writeInt32(19, f19_);
      }
      if (f20_ != 0) {
        output.writeInt32(20, f20_);
      }
      if (progress_ != 0) {
        output.writeInt32(21, progress_);
      }
      if (customisationId_ != 0L) {
        output.writeInt64(22, customisationId_);
      }
      if (justWatching_ != 0) {
        output.writeInt32(23, justWatching_);
      }
      if (calories_ != 0) {
        output.writeInt32(24, calories_);
      }
      if (x_ != 0F) {
        output.writeFloat(25, x_);
      }
      if (altitude_ != 0F) {
        output.writeFloat(26, altitude_);
      }
      if (y_ != 0F) {
        output.writeFloat(27, y_);
      }
      if (watchingRiderId_ != 0) {
        output.writeInt32(28, watchingRiderId_);
      }
      if (groupId_ != 0) {
        output.writeInt32(29, groupId_);
      }
      if (sport_ != 0L) {
        output.writeInt64(31, sport_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (id_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, id_);
      }
      if (worldTime_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, worldTime_);
      }
      if (distance_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, distance_);
      }
      if (roadTime_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, roadTime_);
      }
      if (laps_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, laps_);
      }
      if (speed_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, speed_);
      }
      if (roadPosition_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(8, roadPosition_);
      }
      if (cadenceUHz_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(9, cadenceUHz_);
      }
      if (heartrate_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(11, heartrate_);
      }
      if (power_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(12, power_);
      }
      if (heading_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(13, heading_);
      }
      if (lean_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(14, lean_);
      }
      if (climbing_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(15, climbing_);
      }
      if (time_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(16, time_);
      }
      if (f19_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(19, f19_);
      }
      if (f20_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(20, f20_);
      }
      if (progress_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(21, progress_);
      }
      if (customisationId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(22, customisationId_);
      }
      if (justWatching_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(23, justWatching_);
      }
      if (calories_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(24, calories_);
      }
      if (x_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(25, x_);
      }
      if (altitude_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(26, altitude_);
      }
      if (y_ != 0F) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(27, y_);
      }
      if (watchingRiderId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(28, watchingRiderId_);
      }
      if (groupId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(29, groupId_);
      }
      if (sport_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(31, sport_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState)) {
        return super.equals(obj);
      }
      com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState other = (com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && (getWorldTime()
          == other.getWorldTime());
      result = result && (getDistance()
          == other.getDistance());
      result = result && (getRoadTime()
          == other.getRoadTime());
      result = result && (getLaps()
          == other.getLaps());
      result = result && (getSpeed()
          == other.getSpeed());
      result = result && (getRoadPosition()
          == other.getRoadPosition());
      result = result && (getCadenceUHz()
          == other.getCadenceUHz());
      result = result && (getHeartrate()
          == other.getHeartrate());
      result = result && (getPower()
          == other.getPower());
      result = result && (getHeading()
          == other.getHeading());
      result = result && (getLean()
          == other.getLean());
      result = result && (getClimbing()
          == other.getClimbing());
      result = result && (getTime()
          == other.getTime());
      result = result && (getF19()
          == other.getF19());
      result = result && (getF20()
          == other.getF20());
      result = result && (getProgress()
          == other.getProgress());
      result = result && (getCustomisationId()
          == other.getCustomisationId());
      result = result && (getJustWatching()
          == other.getJustWatching());
      result = result && (getCalories()
          == other.getCalories());
      result = result && (
          java.lang.Float.floatToIntBits(getX())
          == java.lang.Float.floatToIntBits(
              other.getX()));
      result = result && (
          java.lang.Float.floatToIntBits(getAltitude())
          == java.lang.Float.floatToIntBits(
              other.getAltitude()));
      result = result && (
          java.lang.Float.floatToIntBits(getY())
          == java.lang.Float.floatToIntBits(
              other.getY()));
      result = result && (getWatchingRiderId()
          == other.getWatchingRiderId());
      result = result && (getGroupId()
          == other.getGroupId());
      result = result && (getSport()
          == other.getSport());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
      hash = (37 * hash) + WORLDTIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getWorldTime());
      hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
      hash = (53 * hash) + getDistance();
      hash = (37 * hash) + ROADTIME_FIELD_NUMBER;
      hash = (53 * hash) + getRoadTime();
      hash = (37 * hash) + LAPS_FIELD_NUMBER;
      hash = (53 * hash) + getLaps();
      hash = (37 * hash) + SPEED_FIELD_NUMBER;
      hash = (53 * hash) + getSpeed();
      hash = (37 * hash) + ROADPOSITION_FIELD_NUMBER;
      hash = (53 * hash) + getRoadPosition();
      hash = (37 * hash) + CADENCEUHZ_FIELD_NUMBER;
      hash = (53 * hash) + getCadenceUHz();
      hash = (37 * hash) + HEARTRATE_FIELD_NUMBER;
      hash = (53 * hash) + getHeartrate();
      hash = (37 * hash) + POWER_FIELD_NUMBER;
      hash = (53 * hash) + getPower();
      hash = (37 * hash) + HEADING_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHeading());
      hash = (37 * hash) + LEAN_FIELD_NUMBER;
      hash = (53 * hash) + getLean();
      hash = (37 * hash) + CLIMBING_FIELD_NUMBER;
      hash = (53 * hash) + getClimbing();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime();
      hash = (37 * hash) + F19_FIELD_NUMBER;
      hash = (53 * hash) + getF19();
      hash = (37 * hash) + F20_FIELD_NUMBER;
      hash = (53 * hash) + getF20();
      hash = (37 * hash) + PROGRESS_FIELD_NUMBER;
      hash = (53 * hash) + getProgress();
      hash = (37 * hash) + CUSTOMISATIONID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getCustomisationId());
      hash = (37 * hash) + JUSTWATCHING_FIELD_NUMBER;
      hash = (53 * hash) + getJustWatching();
      hash = (37 * hash) + CALORIES_FIELD_NUMBER;
      hash = (53 * hash) + getCalories();
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getX());
      hash = (37 * hash) + ALTITUDE_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getAltitude());
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + java.lang.Float.floatToIntBits(
          getY());
      hash = (37 * hash) + WATCHINGRIDERID_FIELD_NUMBER;
      hash = (53 * hash) + getWatchingRiderId();
      hash = (37 * hash) + GROUPID_FIELD_NUMBER;
      hash = (53 * hash) + getGroupId();
      hash = (37 * hash) + SPORT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSport());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code zwift.PlayerState}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:zwift.PlayerState)
        com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerStateOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return internal_static_zwift_PlayerState_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return internal_static_zwift_PlayerState_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState.class, com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState.Builder.class);
      }

      // Construct using com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        id_ = 0;

        worldTime_ = 0L;

        distance_ = 0;

        roadTime_ = 0;

        laps_ = 0;

        speed_ = 0;

        roadPosition_ = 0;

        cadenceUHz_ = 0;

        heartrate_ = 0;

        power_ = 0;

        heading_ = 0L;

        lean_ = 0;

        climbing_ = 0;

        time_ = 0;

        f19_ = 0;

        f20_ = 0;

        progress_ = 0;

        customisationId_ = 0L;

        justWatching_ = 0;

        calories_ = 0;

        x_ = 0F;

        altitude_ = 0F;

        y_ = 0F;

        watchingRiderId_ = 0;

        groupId_ = 0;

        sport_ = 0L;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return internal_static_zwift_PlayerState_descriptor;
      }

      public com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState getDefaultInstanceForType() {
        return getDefaultInstance();
      }

      public com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState build() {
        com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState buildPartial() {
        com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState result = new com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState(this);
        result.id_ = id_;
        result.worldTime_ = worldTime_;
        result.distance_ = distance_;
        result.roadTime_ = roadTime_;
        result.laps_ = laps_;
        result.speed_ = speed_;
        result.roadPosition_ = roadPosition_;
        result.cadenceUHz_ = cadenceUHz_;
        result.heartrate_ = heartrate_;
        result.power_ = power_;
        result.heading_ = heading_;
        result.lean_ = lean_;
        result.climbing_ = climbing_;
        result.time_ = time_;
        result.f19_ = f19_;
        result.f20_ = f20_;
        result.progress_ = progress_;
        result.customisationId_ = customisationId_;
        result.justWatching_ = justWatching_;
        result.calories_ = calories_;
        result.x_ = x_;
        result.altitude_ = altitude_;
        result.y_ = y_;
        result.watchingRiderId_ = watchingRiderId_;
        result.groupId_ = groupId_;
        result.sport_ = sport_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState) {
          return mergeFrom((com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState other) {
        if (other == getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (other.getWorldTime() != 0L) {
          setWorldTime(other.getWorldTime());
        }
        if (other.getDistance() != 0) {
          setDistance(other.getDistance());
        }
        if (other.getRoadTime() != 0) {
          setRoadTime(other.getRoadTime());
        }
        if (other.getLaps() != 0) {
          setLaps(other.getLaps());
        }
        if (other.getSpeed() != 0) {
          setSpeed(other.getSpeed());
        }
        if (other.getRoadPosition() != 0) {
          setRoadPosition(other.getRoadPosition());
        }
        if (other.getCadenceUHz() != 0) {
          setCadenceUHz(other.getCadenceUHz());
        }
        if (other.getHeartrate() != 0) {
          setHeartrate(other.getHeartrate());
        }
        if (other.getPower() != 0) {
          setPower(other.getPower());
        }
        if (other.getHeading() != 0L) {
          setHeading(other.getHeading());
        }
        if (other.getLean() != 0) {
          setLean(other.getLean());
        }
        if (other.getClimbing() != 0) {
          setClimbing(other.getClimbing());
        }
        if (other.getTime() != 0) {
          setTime(other.getTime());
        }
        if (other.getF19() != 0) {
          setF19(other.getF19());
        }
        if (other.getF20() != 0) {
          setF20(other.getF20());
        }
        if (other.getProgress() != 0) {
          setProgress(other.getProgress());
        }
        if (other.getCustomisationId() != 0L) {
          setCustomisationId(other.getCustomisationId());
        }
        if (other.getJustWatching() != 0) {
          setJustWatching(other.getJustWatching());
        }
        if (other.getCalories() != 0) {
          setCalories(other.getCalories());
        }
        if (other.getX() != 0F) {
          setX(other.getX());
        }
        if (other.getAltitude() != 0F) {
          setAltitude(other.getAltitude());
        }
        if (other.getY() != 0F) {
          setY(other.getY());
        }
        if (other.getWatchingRiderId() != 0) {
          setWatchingRiderId(other.getWatchingRiderId());
        }
        if (other.getGroupId() != 0) {
          setGroupId(other.getGroupId());
        }
        if (other.getSport() != 0L) {
          setSport(other.getSport());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int id_ ;
      /**
       * <code>int32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>int32 id = 1;</code>
       */
      public Builder setId(int value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 id = 1;</code>
       */
      public Builder clearId() {
        
        id_ = 0;
        onChanged();
        return this;
      }

      private long worldTime_ ;
      /**
       * <code>int64 worldTime = 2;</code>
       */
      public long getWorldTime() {
        return worldTime_;
      }
      /**
       * <code>int64 worldTime = 2;</code>
       */
      public Builder setWorldTime(long value) {
        
        worldTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 worldTime = 2;</code>
       */
      public Builder clearWorldTime() {
        
        worldTime_ = 0L;
        onChanged();
        return this;
      }

      private int distance_ ;
      /**
       * <code>int32 distance = 3;</code>
       */
      public int getDistance() {
        return distance_;
      }
      /**
       * <code>int32 distance = 3;</code>
       */
      public Builder setDistance(int value) {
        
        distance_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 distance = 3;</code>
       */
      public Builder clearDistance() {
        
        distance_ = 0;
        onChanged();
        return this;
      }

      private int roadTime_ ;
      /**
       * <code>int32 roadTime = 4;</code>
       */
      public int getRoadTime() {
        return roadTime_;
      }
      /**
       * <code>int32 roadTime = 4;</code>
       */
      public Builder setRoadTime(int value) {
        
        roadTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 roadTime = 4;</code>
       */
      public Builder clearRoadTime() {
        
        roadTime_ = 0;
        onChanged();
        return this;
      }

      private int laps_ ;
      /**
       * <code>int32 laps = 5;</code>
       */
      public int getLaps() {
        return laps_;
      }
      /**
       * <code>int32 laps = 5;</code>
       */
      public Builder setLaps(int value) {
        
        laps_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 laps = 5;</code>
       */
      public Builder clearLaps() {
        
        laps_ = 0;
        onChanged();
        return this;
      }

      private int speed_ ;
      /**
       * <code>int32 speed = 6;</code>
       */
      public int getSpeed() {
        return speed_;
      }
      /**
       * <code>int32 speed = 6;</code>
       */
      public Builder setSpeed(int value) {
        
        speed_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 speed = 6;</code>
       */
      public Builder clearSpeed() {
        
        speed_ = 0;
        onChanged();
        return this;
      }

      private int roadPosition_ ;
      /**
       * <code>int32 roadPosition = 8;</code>
       */
      public int getRoadPosition() {
        return roadPosition_;
      }
      /**
       * <code>int32 roadPosition = 8;</code>
       */
      public Builder setRoadPosition(int value) {
        
        roadPosition_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 roadPosition = 8;</code>
       */
      public Builder clearRoadPosition() {
        
        roadPosition_ = 0;
        onChanged();
        return this;
      }

      private int cadenceUHz_ ;
      /**
       * <code>int32 cadenceUHz = 9;</code>
       */
      public int getCadenceUHz() {
        return cadenceUHz_;
      }
      /**
       * <code>int32 cadenceUHz = 9;</code>
       */
      public Builder setCadenceUHz(int value) {
        
        cadenceUHz_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 cadenceUHz = 9;</code>
       */
      public Builder clearCadenceUHz() {
        
        cadenceUHz_ = 0;
        onChanged();
        return this;
      }

      private int heartrate_ ;
      /**
       * <code>int32 heartrate = 11;</code>
       */
      public int getHeartrate() {
        return heartrate_;
      }
      /**
       * <code>int32 heartrate = 11;</code>
       */
      public Builder setHeartrate(int value) {
        
        heartrate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 heartrate = 11;</code>
       */
      public Builder clearHeartrate() {
        
        heartrate_ = 0;
        onChanged();
        return this;
      }

      private int power_ ;
      /**
       * <code>int32 power = 12;</code>
       */
      public int getPower() {
        return power_;
      }
      /**
       * <code>int32 power = 12;</code>
       */
      public Builder setPower(int value) {
        
        power_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 power = 12;</code>
       */
      public Builder clearPower() {
        
        power_ = 0;
        onChanged();
        return this;
      }

      private long heading_ ;
      /**
       * <code>int64 heading = 13;</code>
       */
      public long getHeading() {
        return heading_;
      }
      /**
       * <code>int64 heading = 13;</code>
       */
      public Builder setHeading(long value) {
        
        heading_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 heading = 13;</code>
       */
      public Builder clearHeading() {
        
        heading_ = 0L;
        onChanged();
        return this;
      }

      private int lean_ ;
      /**
       * <code>int32 lean = 14;</code>
       */
      public int getLean() {
        return lean_;
      }
      /**
       * <code>int32 lean = 14;</code>
       */
      public Builder setLean(int value) {
        
        lean_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 lean = 14;</code>
       */
      public Builder clearLean() {
        
        lean_ = 0;
        onChanged();
        return this;
      }

      private int climbing_ ;
      /**
       * <code>int32 climbing = 15;</code>
       */
      public int getClimbing() {
        return climbing_;
      }
      /**
       * <code>int32 climbing = 15;</code>
       */
      public Builder setClimbing(int value) {
        
        climbing_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 climbing = 15;</code>
       */
      public Builder clearClimbing() {
        
        climbing_ = 0;
        onChanged();
        return this;
      }

      private int time_ ;
      /**
       * <code>int32 time = 16;</code>
       */
      public int getTime() {
        return time_;
      }
      /**
       * <code>int32 time = 16;</code>
       */
      public Builder setTime(int value) {
        
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 time = 16;</code>
       */
      public Builder clearTime() {
        
        time_ = 0;
        onChanged();
        return this;
      }

      private int f19_ ;
      /**
       * <code>int32 f19 = 19;</code>
       */
      public int getF19() {
        return f19_;
      }
      /**
       * <code>int32 f19 = 19;</code>
       */
      public Builder setF19(int value) {
        
        f19_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 f19 = 19;</code>
       */
      public Builder clearF19() {
        
        f19_ = 0;
        onChanged();
        return this;
      }

      private int f20_ ;
      /**
       * <code>int32 f20 = 20;</code>
       */
      public int getF20() {
        return f20_;
      }
      /**
       * <code>int32 f20 = 20;</code>
       */
      public Builder setF20(int value) {
        
        f20_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 f20 = 20;</code>
       */
      public Builder clearF20() {
        
        f20_ = 0;
        onChanged();
        return this;
      }

      private int progress_ ;
      /**
       * <code>int32 progress = 21;</code>
       */
      public int getProgress() {
        return progress_;
      }
      /**
       * <code>int32 progress = 21;</code>
       */
      public Builder setProgress(int value) {
        
        progress_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 progress = 21;</code>
       */
      public Builder clearProgress() {
        
        progress_ = 0;
        onChanged();
        return this;
      }

      private long customisationId_ ;
      /**
       * <code>int64 customisationId = 22;</code>
       */
      public long getCustomisationId() {
        return customisationId_;
      }
      /**
       * <code>int64 customisationId = 22;</code>
       */
      public Builder setCustomisationId(long value) {
        
        customisationId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 customisationId = 22;</code>
       */
      public Builder clearCustomisationId() {
        
        customisationId_ = 0L;
        onChanged();
        return this;
      }

      private int justWatching_ ;
      /**
       * <code>int32 justWatching = 23;</code>
       */
      public int getJustWatching() {
        return justWatching_;
      }
      /**
       * <code>int32 justWatching = 23;</code>
       */
      public Builder setJustWatching(int value) {
        
        justWatching_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 justWatching = 23;</code>
       */
      public Builder clearJustWatching() {
        
        justWatching_ = 0;
        onChanged();
        return this;
      }

      private int calories_ ;
      /**
       * <code>int32 calories = 24;</code>
       */
      public int getCalories() {
        return calories_;
      }
      /**
       * <code>int32 calories = 24;</code>
       */
      public Builder setCalories(int value) {
        
        calories_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 calories = 24;</code>
       */
      public Builder clearCalories() {
        
        calories_ = 0;
        onChanged();
        return this;
      }

      private float x_ ;
      /**
       * <code>float x = 25;</code>
       */
      public float getX() {
        return x_;
      }
      /**
       * <code>float x = 25;</code>
       */
      public Builder setX(float value) {
        
        x_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float x = 25;</code>
       */
      public Builder clearX() {
        
        x_ = 0F;
        onChanged();
        return this;
      }

      private float altitude_ ;
      /**
       * <code>float altitude = 26;</code>
       */
      public float getAltitude() {
        return altitude_;
      }
      /**
       * <code>float altitude = 26;</code>
       */
      public Builder setAltitude(float value) {
        
        altitude_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float altitude = 26;</code>
       */
      public Builder clearAltitude() {
        
        altitude_ = 0F;
        onChanged();
        return this;
      }

      private float y_ ;
      /**
       * <code>float y = 27;</code>
       */
      public float getY() {
        return y_;
      }
      /**
       * <code>float y = 27;</code>
       */
      public Builder setY(float value) {
        
        y_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>float y = 27;</code>
       */
      public Builder clearY() {
        
        y_ = 0F;
        onChanged();
        return this;
      }

      private int watchingRiderId_ ;
      /**
       * <code>int32 watchingRiderId = 28;</code>
       */
      public int getWatchingRiderId() {
        return watchingRiderId_;
      }
      /**
       * <code>int32 watchingRiderId = 28;</code>
       */
      public Builder setWatchingRiderId(int value) {
        
        watchingRiderId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 watchingRiderId = 28;</code>
       */
      public Builder clearWatchingRiderId() {
        
        watchingRiderId_ = 0;
        onChanged();
        return this;
      }

      private int groupId_ ;
      /**
       * <code>int32 groupId = 29;</code>
       */
      public int getGroupId() {
        return groupId_;
      }
      /**
       * <code>int32 groupId = 29;</code>
       */
      public Builder setGroupId(int value) {
        
        groupId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 groupId = 29;</code>
       */
      public Builder clearGroupId() {
        
        groupId_ = 0;
        onChanged();
        return this;
      }

      private long sport_ ;
      /**
       * <code>int64 sport = 31;</code>
       */
      public long getSport() {
        return sport_;
      }
      /**
       * <code>int64 sport = 31;</code>
       */
      public Builder setSport(long value) {
        
        sport_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 sport = 31;</code>
       */
      public Builder clearSport() {
        
        sport_ = 0L;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:zwift.PlayerState)
    }

    // @@protoc_insertion_point(class_scope:zwift.PlayerState)
    private static final com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState();
    }

    public static com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PlayerState>
        PARSER = new com.google.protobuf.AbstractParser<PlayerState>() {
      public PlayerState parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new PlayerState(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PlayerState> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerState> getParserForType() {
      return PARSER;
    }

    public com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages.PlayerState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_zwift_PlayerState_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_zwift_PlayerState_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n+main/resources/protobuf/zwiftMessages." +
      "proto\022\005zwift\"\306\003\n\013PlayerState\022\n\n\002id\030\001 \001(\005" +
      "\022\021\n\tworldTime\030\002 \001(\003\022\020\n\010distance\030\003 \001(\005\022\020\n" +
      "\010roadTime\030\004 \001(\005\022\014\n\004laps\030\005 \001(\005\022\r\n\005speed\030\006" +
      " \001(\005\022\024\n\014roadPosition\030\010 \001(\005\022\022\n\ncadenceUHz" +
      "\030\t \001(\005\022\021\n\theartrate\030\013 \001(\005\022\r\n\005power\030\014 \001(\005" +
      "\022\017\n\007heading\030\r \001(\003\022\014\n\004lean\030\016 \001(\005\022\020\n\010climb" +
      "ing\030\017 \001(\005\022\014\n\004time\030\020 \001(\005\022\013\n\003f19\030\023 \001(\005\022\013\n\003" +
      "f20\030\024 \001(\005\022\020\n\010progress\030\025 \001(\005\022\027\n\017customisa" +
      "tionId\030\026 \001(\003\022\024\n\014justWatching\030\027 \001(\005\022\020\n\010ca",
      "lories\030\030 \001(\005\022\t\n\001x\030\031 \001(\002\022\020\n\010altitude\030\032 \001(" +
      "\002\022\t\n\001y\030\033 \001(\002\022\027\n\017watchingRiderId\030\034 \001(\005\022\017\n" +
      "\007groupId\030\035 \001(\005\022\r\n\005sport\030\037 \001(\003B-\n+com.val" +
      "verde.sporttrainerserver.zwift.modelb\006pr" +
      "oto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_zwift_PlayerState_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_zwift_PlayerState_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_zwift_PlayerState_descriptor,
        new java.lang.String[] { "Id", "WorldTime", "Distance", "RoadTime", "Laps", "Speed", "RoadPosition", "CadenceUHz", "Heartrate", "Power", "Heading", "Lean", "Climbing", "Time", "F19", "F20", "Progress", "CustomisationId", "JustWatching", "Calories", "X", "Altitude", "Y", "WatchingRiderId", "GroupId", "Sport", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
