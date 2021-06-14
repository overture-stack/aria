/*
 * Copyright (c) 2021 The Ontario Institute for Cancer Research. All rights reserved
 *
 * This program and the accompanying materials are made available under the terms of the GNU Affero General Public License v3.0.
 * You should have received a copy of the GNU Affero General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package bio.overture.aria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Analysis {
  private static final String PUBLISHED_STATE = "PUBLISHED";

  private String analysisId;
  private String studyId;
  private AnalysisState analysisState;
  private AnalysisType analysisType;
  private List<Sample> samples;
  private List<AnalysisFile> files;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private LocalDateTime firstPublishedAt;
  private LocalDateTime publishedAt;

  private SortedSet<AnalysisStateChange> analysisStateHistory;

  private Map<String, Object> data;

  public Boolean isPublished() {
    return analysisState.equals(AnalysisState.PUBLISHED);
  }

  public Boolean hasFiles() {
    return files.size() > 0;
  }

  public enum AnalysisState {
    PUBLISHED,
    UNPUBLISHED,
    SUPPRESSED;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AnalysisType {
    @NotNull private String name;
    @Positive private Integer version;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Sample {
    private String sampleId;
    private String specimenId;
    private String submitterSampleId;
    private String sampleType;
    private String matchedNormalSubmitterSampleId;
    private Specimen specimen;
    private Donor donor;
    private Map<String, Object> info;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Specimen {
    private String specimenId;
    private String donorId;
    private String submitterSpecimenId;
    private String tumourNormalDesignation;
    private String specimenTissueSource;
    private String specimenType;
    private Map<String, Object> info;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Donor {
    private String donorId;
    private String studyId;
    private String submitterDonorId;
    private String gender;
    private Map<String, Object> info;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AnalysisStateChange implements Comparable<AnalysisStateChange> {
    private String initialState;
    private String updatedState;
    private LocalDateTime updatedAt;
    private Map<String, Object> data;

    @Override
    public int compareTo(AnalysisStateChange o) {
      return this.getUpdatedAt().compareTo(o.getUpdatedAt());
    }
  }
}
